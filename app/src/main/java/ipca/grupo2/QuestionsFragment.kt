package ipca.grupo2

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment


class QuestionsFragment :   DialogFragment() {


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.y = 300 //This will set margin top of 50dp
        window?.attributes = params
        window?.setWindowAnimations(R.style.DialogAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_questions, container, false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        /*val Cabeca = view.findViewById<Button>(R.id)
        val Nausea = view.findViewById<Button>(R.id.proximo)
        val Dormir = view.findViewById<Button>(R.id.proximo)
        val Apetite = view.findViewById<Button>(R.id.proximo) */

        val window = dialog?.window
        val params = window?.attributes
        window?.attributes = params
        val width = (resources.displayMetrics.widthPixels * 0.9f).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.7f).toInt()
        params?.width = width
        params?.height = height

        getDialog()?.getWindow()?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        view.findViewById<Button>(R.id.submeter).setOnClickListener {
            dismiss()
        }

        return view
    }

}