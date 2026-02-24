package ps.mohe.employeemanagement

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDB(context: Context) : SQLiteOpenHelper(context, DB, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE $TBL ($E_ID INTEGER PRIMARY KEY AUTOINCREMENT, $E_NAME TEXT NOT NULL, $E_ROLE TEXT NOT NULL, $E_SALARY DOUBLE);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL")
        onCreate(db)
    }

    companion object{
        val E_ID = "id"
        val E_NAME = "name"
        val E_ROLE = "role"
        val E_SALARY = "salary"
        val TBL = "employees"

        private val DB = "data.db"
        private val VERSION = 1
    }

    fun addEmp(e: Employee){
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(E_NAME, e.name)
        cv.put(E_ROLE, e.role)
        cv.put(E_SALARY, e.salary)

        db.insert(TBL, null, cv)
        db.close()
    }

    fun removeEmp(e: Employee){
        val db = writableDatabase
        db.execSQL("delete from $TBL where $E_ID = ${e.id};")
    }

    fun show(): ArrayList<Employee> {
        val db = readableDatabase
        val Employees = ArrayList<Employee>()
        val c = db.rawQuery("select * from $TBL;", null)

        if (c.moveToFirst()) {
            do {
                val id = c.getInt(c.getColumnIndexOrThrow(E_ID))
                val name = c.getString(c.getColumnIndexOrThrow(E_NAME))
                val salary = c.getDouble(c.getColumnIndexOrThrow(E_SALARY))
                val role = c.getString(c.getColumnIndexOrThrow(E_ROLE))

                val e = Employee(id, name, role, salary)
                Employees.add(e)

            } while (c.moveToNext())
        }

        c.close()
        db.close()
        return Employees
    }

    fun search(e: Employee) : ArrayList<Employee>{
        val db = readableDatabase
        val Employees = ArrayList<Employee>()
        val c = db.rawQuery("select * from $TBL where $E_NAME = \"${e.name}\" or $E_ROLE = \"${e.role}\";", null)

        if (c.moveToFirst()) {
            do {
                val id = c.getInt(c.getColumnIndexOrThrow(E_ID))
                val name = c.getString(c.getColumnIndexOrThrow(E_NAME))
                val salary = c.getDouble(c.getColumnIndexOrThrow(E_SALARY))
                val role = c.getString(c.getColumnIndexOrThrow(E_ROLE))

                val emp = Employee(id, name, role, salary)
                Employees.add(emp)

            } while (c.moveToNext())
        }

        c.close()
        db.close()
        return Employees
    }
}