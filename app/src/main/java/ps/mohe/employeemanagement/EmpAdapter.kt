package ps.mohe.employeemanagement

import android.content.Context
import android.content.Intent
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

        view.infoBtn.setOnClickListener {
            val sph = mapOf(
                "عامل" to 5.0,
                "موظف" to 7.0,
                "مدير" to 10.0
            )

            val salaryph = sph[e.role] ?: 0.0
            val time = (e.salary ?: 0.0) / (30 * salaryph)

            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra("name", e.name)
            intent.putExtra("role", e.role)
            intent.putExtra("dailyTime", time)
           context.startActivity(intent)
        }
        return view
    }
}