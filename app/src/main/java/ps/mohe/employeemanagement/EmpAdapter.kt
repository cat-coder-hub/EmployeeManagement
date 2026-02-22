package ps.mohe.employeemanagement

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item.view.*

class EmpAdapter(context: Context, val objects: ArrayList<Employee>) :
    ArrayAdapter<Employee>(context, R.layout.item, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val l = LayoutInflater.from(context)
        val view = l.inflate(R.layout.item, parent, false)
        val db = MyDB(context)
        val e = objects[position]

        view.empN.text = e.name
        view.empS.text = (e.salary).toString() + "$"
        val image = when(e.role){
            "عامل" -> R.drawable.worker
            "موظف" -> R.drawable.employee
            else -> R.drawable.admin
        }
        view.empImage.setImageResource(image)

        view.deleteBtn.setOnClickListener {
            db.removeEmp(e)
            objects.removeAt(position)
            notifyDataSetChanged()
        }
        return view
    }
}