package com.chuntian.composecookbookcopy.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Suppress("FunctionName")
fun IOScope() = CoroutineScope(SupervisorJob() + Dispatchers.IO)