package ipca.grupo2

import android.app.Activity
import android.app.AlertDialog

class AlertDialog internal constructor(var activity: Activity) {


    var dialog: AlertDialog? = null
    fun startLoadingDialog() {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog!!.show()
    }

    fun dismissDialog() {
        dialog!!.dismiss()
    }
}
