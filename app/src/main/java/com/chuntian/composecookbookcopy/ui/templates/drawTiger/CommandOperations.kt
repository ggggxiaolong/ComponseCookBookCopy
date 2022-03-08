package com.chuntian.composecookbookcopy.ui.templates.drawTiger

import android.graphics.PointF
import kotlin.math.*

/**
 * Object tha contain methods for the conversion of all types to the 'C' type
 * that can then be drawn with the cubicTo() method on any canvas. And methods
 * for the string data extraction to the expected commands.
 */
object CommandOperations {

    private const val TAU: Float = PI.toFloat() * 2.0f

    // temp points used in the conversion, instead of creating new one each time
    internal val point = PointF()
    internal val quad = PointF()
    internal val start = PointF()
    internal val bezier = PointF()



    /**
     * Convert from endpoint to center parameterization
     * @return array list {cx, cy, theta1, deltaTheta}
     */
    private fun getArcCenter(
        x1: Float, y1: Float, x2: Float, y2: Float, fa: Float, fs: Float,
        rx: Float, ry: Float, sinPhi: Float, cosPhi: Float
    ): FloatArray {

        /**
         *  moving an ellipse so origin will be the middle point
         *  between our two points. After that, rotate it to line
         *  up ellipse axes with coordinate axes.
         */
        val x1p = cosPhi * (x1 - x2) / 2 + sinPhi * (y1 - y2) / 2
        val y1p = -sinPhi * (x1 - x2) / 2 + cosPhi * (y1 - y2) / 2

        val rxSq = rx * rx
        val rySq = ry * ry
        val x1pSq = x1p * x1p
        val y1pSq = y1p * y1p

        // compute coordinates of the centre of this ellipse (cx', cy') in the new coordinate system.
        var radicant = (rxSq * rySq) - (rxSq * y1pSq) - (rySq * x1pSq)
        if (radicant < 0.0f) {
            // due to rounding errors it might be e.g. -1.3877787807814457e-17
            radicant = 0.0f
        }

        radicant /= (rxSq * y1pSq) + (rySq * x1pSq)
        radicant = sqrt(radicant.toDouble()).toFloat() * (if (fa == fs) -1 else 1)

        val cxp = radicant * rx / ry * y1p
        val cyp = radicant * -ry / rx * x1p

        // transform back to get centre coordinates (cx, cy) in the original coordinate system.
        val cx = cosPhi * cxp - sinPhi * cyp + (x1 + x2) / 2
        val cy = sinPhi * cxp + cosPhi * cyp + (y1 + y2) / 2

        // compute angles (theta1, deltaTheta)
        val v1x = (x1p - cxp) / rx
        val v1y = (y1p - cyp) / ry
        val v2x = (-x1p - cxp) / rx
        val v2y = (-y1p - cyp) / ry

        val theta1 = unitVectorAngle(1.0f, 0.0f, v1x, v1y)
        var deltaTheta = unitVectorAngle(v1x, v1y, v2x, v2y)

        if (fs == 0.0f && deltaTheta > 0.0f) {
            deltaTheta -= TAU
        }
        if (fs == 1.0f && deltaTheta < 0.0f) {
            deltaTheta += TAU
        }

        return floatArrayOf(cx, cy, theta1, deltaTheta)
    }

    /**
     *  Calculate an angle between two unit vectors, since we measure angle
     *  between radii of circular arcs, we can use simplified math
     *  (without length normalization)
     */
    private fun unitVectorAngle(ux: Float, uy: Float, vx: Float, vy: Float): Float {
        val sign = if (ux * vy - uy * vx < 0.0) -1 else 1
        var dot = ux * vx + uy * vy

        // add this to work with arbitrary vectors:
        // dot /= sqrt(ux * ux + uy * uy) * sqrt(vx * vx + vy * vy);
        // rounding errors, e.g. -1.0000000000000002 can screw up this
        if (dot > 1.0f) {
            dot = 1.0f
        }
        if (dot < -1.0f) {
            dot = -1.0f
        }

        return (sign * acos(dot.toDouble())).toFloat()
    }

    /**
     * Approximate one unit arc segment with bézier curves
     */
    private fun approximateUnitArc(theta1: Double, deltaTheta: Double): FloatArray {

        val alpha = 4.0f / 3.0f * tan(deltaTheta / 4.0).toFloat()

        val x1 = cos(theta1).toFloat()
        val y1 = sin(theta1).toFloat()
        val x2 = cos(theta1 + deltaTheta).toFloat()
        val y2 = sin(theta1 + deltaTheta).toFloat()

        return floatArrayOf(
            x1, y1,
            x1 - y1 * alpha, y1 + x1 * alpha,
            x2 + y2 * alpha, y2 - x2 * alpha,
            x2, y2
        )
    }

    /**
     * Converts elliptical art, which is type 'A' to a curve which is the
     * expected type 'C' after the conversion
     */
    fun ellipticalArcToCurve(
        x1: Float, y1: Float, x2: Float, y2: Float,
        fa: Float, fs: Float, rx: Float, ry: Float, phi: Float
    ): ArrayList<FloatArray> {

        val sinPhi = sin(phi * TAU / 360.0).toFloat()
        val cosPhi = cos(phi * TAU / 360.0).toFloat()

        // make sure radii are valid
        val x1p = cosPhi * (x1 - x2) / 2.0 + sinPhi * (y1 - y2) / 2.0
        val y1p = -sinPhi * (x1 - x2) / 2.0 + cosPhi * (y1 - y2) / 2.0

        if (x1p == 0.0 && y1p == 0.0) {
            // we're asked to draw line to itself
            return arrayListOf()
        }

        if (rx == 0.0f || ry == 0.0f) {
            // one of the radii is zero
            return arrayListOf()
        }

        // compensate out-of-range radii
        var rxTem = abs(rx)
        var ryTem = abs(ry)

        val lambda = (x1p * x1p) / (rxTem * rxTem) + (y1p * y1p) / (ryTem * ryTem)
        if (lambda > 1) {
            rxTem *= sqrt(lambda).toFloat()
            ryTem *= sqrt(lambda).toFloat()
        }

        // get center parameters (cx, cy, theta1, deltaTheta)
        val cc = getArcCenter(x1, y1, x2, y2, fa, fs, rxTem, ryTem, sinPhi, cosPhi)

        val result = arrayListOf<FloatArray>()
        var theta1 = cc[2]
        var deltaTheta = cc[3]

        // split an arc to multiple segments, so each segment will be less than τ/4 (= 90°)
        val segments = max(ceil(abs(deltaTheta) / (TAU / 4.0)), 1.0).toFloat()
        deltaTheta /= segments

        for (i in 0 until segments.toInt()) {
            result.add(approximateUnitArc(theta1.toDouble(), deltaTheta.toDouble()))
            theta1 += deltaTheta
        }

        fun innerFunc(curve: FloatArray): FloatArray {
            for (i in curve.indices step 2) {
                var x = curve[i + 0]
                var y = curve[i + 1]

                // scale
                x *= rxTem
                y *= ryTem

                // rotate
                val xp = cosPhi * x - sinPhi * y
                val yp = sinPhi * x + cosPhi * y

                // translate
                curve[i + 0] = xp + cc[0]
                curve[i + 1] = yp + cc[1]
            }
            return curve
        }

        // we have a bezier approximation of a unit circle, now need to transform back to the original ellipse
        return result.customMap {
            innerFunc(it)
        }
    }
}
/**
 * Parse the string data, and separate it to the supposing commands
 * tha are then returned as array list
 * @param data string containing the path data
 * @return array list with all corresponding commands
 */
fun parse(data: String): ArrayList<Command> {

    val commands = ArrayList<Command>()
    val splitByLetter = data.split(Regex("(?=[a-zA-Z])"))

    // for each split string
    for (splitText in splitByLetter) {

        if (splitText.isNotEmpty()) {

            // ge the supposing type and coordinates
            val type = splitText[0].code
            val typeUpperCase = splitText[0].uppercaseChar().code
            val coordinates = Command.parseIntsAndDoubles(splitText.subSequence(1, splitText.length).toString())

            if (typeUpperCase == TYPE_Z) {
                commands.add(Command(TYPE_Z))
            } else {
                val steps = numberOfCoordinates[typeUpperCase]

                require(steps != null) {
                    "Unknown svg path type: $type"
                }

                require(steps > 0 && coordinates.size % steps == 0) {
                    "Command with type: ${type.toChar()}, does not match the expected number of parameters: ${numberOfCoordinates[typeUpperCase]}"
                }

                // generate commands by separating the expected number of coordinates for each type
                for (i in coordinates.indices step steps) {
                    commands.add(Command(type = type, coordinates = coordinates.copyOfRange(i, i + steps)))
                }
            }
        }
    }
    return commands
}

/**
 * Extension function that convert a integer value that holds the corresponding char values and
 * try to convert it to upper case. {a = 97} -> {A=65}
 */
fun Int.toUpperCase(): Int {
    return if (this in 97..122) {
        this - 32
    } else {
        this
    }
}

/**
 * Extension function that convert a integer value that holds the corresponding char values and
 * try to convert it to lower case. {A=65} -> {a = 97}
 */
fun Int.toLowerCase(): Int {
    return if (this in 65..90) {
        this + 32
    } else {
        this
    }
}


/**
 * Absolutize the coordinates for all commands, that converts the relative commands to absolute
 * command for example 'v' to 'V', 'h' to 'H'... You can red more about relative and absolute
 * commands here: https://www.w3.org/TR/SVG/paths.html
 * @param commands array list with commands for absolutization
 */
fun absolutize(commands: ArrayList<Command>): ArrayList<Command> {

    // inner function that is used with the custom map method
    fun absolutizeInner(command: Command, start: PointF, point: PointF): Command {

        val type = command.type
        val typeUpper = type.toUpperCase()
        val newCommand = Command(typeUpper, command.coordinates.clone())

        // for relative command, those that have type with lowercase 'a', 'v', 'h', ...
        if (type != typeUpper) {

            when (type) {
                TYPE_a -> {
                    newCommand.coordinates[5] += point.x
                    newCommand.coordinates[6] += point.y
                }
                TYPE_v -> {
                    newCommand.coordinates[0] += point.y
                }
                TYPE_h -> {
                    newCommand.coordinates[0] += point.x
                }
                else -> {
                    var i = 0
                    while (i < newCommand.coordinates.size) {
                        newCommand.coordinates[i++] += point.x
                        newCommand.coordinates[i++] += point.y
                    }
                }
            }
        }

        // update cursor state
        when (typeUpper) {
            TYPE_Z -> {
                point.x = start.x
                point.y = start.y
            }
            TYPE_H -> {
                point.x = newCommand.coordinates[0]
            }
            TYPE_V -> {
                point.y = newCommand.coordinates[0]
            }
            TYPE_M -> {
                point.x = newCommand.coordinates[0]
                point.y = newCommand.coordinates[1]
                start.x = point.x
                start.y = point.y
            }
            else -> {
                point.x = newCommand.coordinates[newCommand.coordinates.size - 2]
                point.y = newCommand.coordinates[newCommand.coordinates.size - 1]
            }
        }

        return newCommand
    }

    return commands.customMap {
        absolutizeInner(it, CommandOperations.start, CommandOperations.point)
    }
}

/**
 * Custom map method, attached to array list. This extension function allows
 * to call method for array list adn that way mapping the values.
 * @param transform transformation function used for updating values
 */
fun <T> ArrayList<T>.customMap(transform: (T) -> T): ArrayList<T> {
    for (i in this.indices) {
        this[i] = transform(this[i])
    }
    return this
}

/**
 * Normalize all commands, by converting them from there original command type to the 'C' type,
 * that can be drawn with the cubicTo method on regular canvas. Exception is the 'M' - move to
 * command, which remains the same, and is only given the proper starting point. That means all
 * commands with types 'V', 'H', 'S'... are converted to type 'C' command.
 * @param commands array list with commands for normalization
 */
fun normalize(commands: ArrayList<Command>): ArrayList<Command> {

    // init state
    var previousType = TYPE_NONE
    val newCommands = ArrayList<Command>()

    loop@ for (i in commands.indices) {

        val command = commands[i]
        val coordinates = command.coordinates
        val typeBeforeChange = command.type

        // set the new command that is either kept as M or changed to C
        val normalizedCommand = when (command.type) {

            // move to
            TYPE_M -> {
                CommandOperations.start.x = coordinates[0]
                CommandOperations.start.y = coordinates[1]
                Command.fromCoordinates(TYPE_M, floatArrayOf(CommandOperations.start.x, CommandOperations.start.y))
            }

            // elliptical arc
            TYPE_A -> {
                val curves = CommandOperations.ellipticalArcToCurve(
                    CommandOperations.point.x, CommandOperations.point.y,
                    coordinates[5], coordinates[6], coordinates[3],
                    coordinates[4], coordinates[0], coordinates[1], coordinates[2]
                )

                if (curves.isEmpty()) continue@loop

                var newCommand = Command()
                for (j in curves.indices) {
                    val c = curves[j]
                    newCommand = Command(TYPE_C, floatArrayOf(c[2], c[3], c[4], c[5], c[6], c[7]))
                    if (j < curves.size - 1) {
                        newCommands.add(newCommand)
                    }
                }
                newCommand
            }

            // shorthand/smooth curve to
            TYPE_S -> {

                // default control point
                var x = CommandOperations.point.x
                var y = CommandOperations.point.y
                if (previousType == TYPE_C || previousType == TYPE_S) {
                    x += x - CommandOperations.bezier.x // reflect the previous commandType's control
                    y += y - CommandOperations.bezier.y // point relative to the current point
                }
                Command(TYPE_C, floatArrayOf(x, y, coordinates[0], coordinates[1], coordinates[2], coordinates[3]))
            }

            // shorthand/smooth quadratic Bézier curve to
            TYPE_T -> {

                if (previousType == TYPE_Q || previousType == TYPE_T) {
                    CommandOperations.quad.x = CommandOperations.point.x * 2 - CommandOperations.quad.x // as with 'S' reflect previous control point
                    CommandOperations.quad.y = CommandOperations.point.y * 2 - CommandOperations.quad.y
                } else {
                    CommandOperations.quad.x = CommandOperations.point.x
                    CommandOperations.quad.y = CommandOperations.point.y
                }
                Command.fromQuadratic(CommandOperations.point.x, CommandOperations.point.y, CommandOperations.quad.x, CommandOperations.quad.y, coordinates[0], coordinates[1])
            }

            // quadratic Bézier curve to
            TYPE_Q -> {
                CommandOperations.quad.x = coordinates[0]
                CommandOperations.quad.y = coordinates[1]
                Command.fromQuadratic(CommandOperations.point.x, CommandOperations.point.y, coordinates[0], coordinates[1], coordinates[2], coordinates[3])
            }

            // line to
            TYPE_L -> {
                Command.fromLine(CommandOperations.point.x, CommandOperations.point.y, coordinates[0], coordinates[1])
            }

            // horizontal line to
            TYPE_H -> {
                Command.fromLine(CommandOperations.point.x, CommandOperations.point.y, coordinates[0], CommandOperations.point.y)
            }

            // vertical line to
            TYPE_V -> {
                Command.fromLine(CommandOperations.point.x, CommandOperations.point.y, CommandOperations.point.x, coordinates[0])
            }

            // close path
            TYPE_Z -> {
                Command.fromLine(CommandOperations.point.x, CommandOperations.point.y, CommandOperations.start.x, CommandOperations.start.y)
            }

            // curve to
            TYPE_C -> {
                Command(TYPE_C, floatArrayOf(coordinates[0], coordinates[1], coordinates[2], coordinates[3], coordinates[4], coordinates[5]))
            }

            // empty command, for unrecognized type
            else -> Command()
        }

        // update states for the next loop
        val normalizedCoordinates = normalizedCommand.coordinates
        val size = normalizedCoordinates.size
        previousType = typeBeforeChange

        // get last point that will be start point for next command
        if (size >= 2) {
            CommandOperations.point.x = normalizedCoordinates[size - 2]
            CommandOperations.point.y = normalizedCoordinates[size - 1]
        }

        if (size >= 4) {
            CommandOperations.bezier.x = normalizedCoordinates[size - 4]
            CommandOperations.bezier.y = normalizedCoordinates[size - 3]
        } else {
            CommandOperations.bezier.x = CommandOperations.point.x
            CommandOperations.bezier.y = CommandOperations.point.y
        }
        newCommands.add(normalizedCommand)
    }

    return newCommands
}