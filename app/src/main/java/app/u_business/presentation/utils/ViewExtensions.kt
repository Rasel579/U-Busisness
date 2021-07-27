package app.u_business.presentation.utils

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import java.util.regex.Matcher
import java.util.regex.Pattern

fun Fragment.navigate(resId: Int, bundle: Bundle? = null) {
    NavHostFragment.findNavController(this).navigate(resId, bundle)
}

fun Fragment.navigate(dir: NavDirections) {
    findNavController().navigate(dir)
}
fun Fragment.showAlertDialog(title: Int, message: Int)  {
    AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok") { dialog, _ -> dialog.cancel() }
        .show()
}

fun EditText.validateEmail(email: String) : Boolean{
    val VALID_EMAIL_ADDRESS_REGEX: Pattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
    val mathcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email)
       return mathcher.find()
}
