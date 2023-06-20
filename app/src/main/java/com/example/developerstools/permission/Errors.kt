package com.example.developerstools.permission

class Errors {
    companion object{
        const val SOLUTION_ERROR_INITIALIZED:String = "Use function defineActions the Manager Permission."

        const val ERROR_INITIALIZED_FUNCTION_ACTION_ACCEPTED:String =
            "Not initialized action function accepted. $SOLUTION_ERROR_INITIALIZED"
        const val ERROR_INITIALIZED_FUNCTION_ACTION_REFUSE:String =
            "Not inicialized action function refuse.  $SOLUTION_ERROR_INITIALIZED"
        const val ERROR_INITIALIZED_FUNCTION_ACTION_MANUAL:String =
            "Not inicialized action function require manual activation. $SOLUTION_ERROR_INITIALIZED"


    }
}