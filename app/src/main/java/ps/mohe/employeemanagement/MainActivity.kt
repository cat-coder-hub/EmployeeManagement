package ps.mohe.employeemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db : MyDB
    lateinit var e : Employee
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MyDB(this)
    }

    fun add(view: View){
        val name = empName.text.toString()
        val role = empRole.text.toString()
        val time = empTime.text.toString().toDoubleOrNull()

        if (name.isEmpty() || (role != "عامل" && role != "مدير" && role != "موظف") || time == null){
            Toast.makeText(this, "تأكد من إدخال جميع القيم بالشكل الصحيح!", Toast.LENGTH_SHORT).show()
        }
        else if (time > 12.0) Toast.makeText(this, "تجاوزت عدد ساعات العمل المسموحة (12 ساعات)!", Toast.LENGTH_SHORT).show()
        else{
            val salary = when(role){
                "عامل" -> 30 * time * 5
                "موظف" -> 30 * time * 7
                else -> 30 * time * 10
            }

            e = Employee(null, name, role, salary)
            db.addEmp(e)
            Toast.makeText(this, "تم إضافة $name بنجاح✅", Toast.LENGTH_SHORT).show()
        }
        empName.text.clear()
        empRole.text.clear()
        empTime.text.clear()
    }

    fun show(view: View) { startActivity(Intent(this, Display::class.java)) }
}