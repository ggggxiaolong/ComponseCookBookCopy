package com.chuntian.composecookbookcopy.ui.home.customFling

import androidx.compose.runtime.Composable
import io.iamjosephmj.flinger.configs.FlingConfiguration
import io.iamjosephmj.flinger.flings.flingBehavior

enum class FlingListViewTypes(_type: Int) {
    // Renders list with native scroll behaviour
    NATIVE(0),

    // Renders list with Smooth Scroll behaviour
    SMOOTH(1),

    // Custom values set by user.
    CUSTOM(2),
}
fun FlingListViewTypes.toName() = when(this){
    FlingListViewTypes.SMOOTH -> "Smooth"
    FlingListViewTypes.NATIVE -> "Native"
    FlingListViewTypes.CUSTOM -> "Custom"
}

data class FlingStateStore(
    var type: FlingListViewTypes = FlingListViewTypes.SMOOTH,
    var scrollFriction: Float = 0.008f,
    var absVelocityThreshold: Float = 0f,
    var gravitationalForce: Float = 9.80665f,
    var inchesPerMeter: Float = 39.37f,
    var decelerationFriction: Float = .09f,
    var decelerationRate: Float = 2.358201f,
    var splineInflection: Float = 0.1f,
    var splineStartTension: Float = 0.1f,
    var splineEndTension: Float = 1.0f,
    var numberOfSplinePoints: Int = 100
) {
    companion object {
        var INSTANCE = FlingStateStore()
    }

    @Composable
    fun buildScrollBehaviour() =
        flingBehavior(
            (FlingConfiguration.Builder()
                .scrollViewFriction(INSTANCE.scrollFriction)
                .absVelocityThreshold(INSTANCE.absVelocityThreshold)
                .gravitationalForce(INSTANCE.gravitationalForce)
                .inchesPerMeter(INSTANCE.inchesPerMeter)
                .decelerationFriction(INSTANCE.decelerationFriction)
                .decelerationRate(INSTANCE.decelerationRate)
                .splineInflection(INSTANCE.splineInflection)
                .splineStartTension(INSTANCE.splineStartTension)
                .splineEndTension(INSTANCE.splineEndTension)
                .numberOfSplinePoints(INSTANCE.numberOfSplinePoints)
                .build())
        )

}