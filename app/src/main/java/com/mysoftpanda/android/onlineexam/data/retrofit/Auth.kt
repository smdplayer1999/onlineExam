package com.mysoftpanda.android.onlineexam.data.retrofit

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.entities.*
import com.mysoftpanda.android.onlineexam.data.retrofit.extensions.MessageData
import com.mysoftpanda.android.onlineexam.data.retrofit.extensions.ResultData
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import com.mysoftpanda.android.onlineexam.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

val localStorage = LocalStorage.instance
private val api = ApiClient.retrofit.create(AuthApi::class.java)
fun login(phoneNumber: String, password: String, block: (ResultData<Any>) -> Unit) {
    localStorage.phoneNumber = "+998$phoneNumber"
    localStorage.password = password
    val contactData = LoginPasswordData(localStorage.phoneNumber, localStorage.password)
    api.login(contactData).enqueue(object : Callback<ResponseData<Any>> {
        override fun onFailure(call: Call<ResponseData<Any>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(
            call: Call<ResponseData<Any>>,
            response: Response<ResponseData<Any>>
        ) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res.data!!))
            }

        }
    })
}

fun remove(block: (ResultData<Any>) -> Unit) {
    val contactData = LoginPasswordData(localStorage.phoneNumber, "1234580")
    api.remove(contactData).enqueue(object : Callback<ResponseData<Any>> {
        override fun onFailure(call: Call<ResponseData<Any>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(
            call: Call<ResponseData<Any>>,
            response: Response<ResponseData<Any>>
        ) {
            val res = response.body()
            when {
                res == null -> block(ResultData.data("succes"))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(""))
            }

        }
    })
}

fun reset(phoneNumber: String, block: (ResultData<Any>) -> Unit) {
    localStorage.phoneNumber = "+998$phoneNumber"
    api.reset(PhoneData("+998$phoneNumber")).enqueue(object : Callback<RegisterData> {
        override fun onFailure(call: Call<RegisterData>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res))
            }
        }
    })
}

fun resend(block: (ResultData<Any>) -> Unit) {
    api.resend(PhoneData(localStorage.phoneNumber)).enqueue(object : Callback<RegisterData> {
        override fun onFailure(call: Call<RegisterData>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res))
            }

        }
    })
}

fun register(
    phoneNumber: String,
    password: String,
    lastName: String,
    firstName: String,
    block: (ResultData<Any>) -> Unit
) {
    localStorage.phoneNumber = "+998$phoneNumber"
    localStorage.password = password
    localStorage.lastName = lastName
    localStorage.firstName = firstName
    api.registerUser(RegisterUserData("+998$phoneNumber", password, lastName, firstName))
        .enqueue(object : Callback<RegisterData> {
            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                block(ResultData.failure(t))
            }

            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                val res = response.body()
                when {
                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res.status == "ERROR" -> block(ResultData.message(res.message))
                    res.status == "OK" -> block(ResultData.data(res))
                }

            }
        })
}

fun newPassword(password: String, code: String, block: (ResultData<Any>) -> Unit) {

    api.resetPassword(ResetPasswordData(localStorage.phoneNumber, password, code))
        .enqueue(object : Callback<ResponseData<Any>> {
            override fun onFailure(call: Call<ResponseData<Any>>, t: Throwable) {
                block(ResultData.failure(t))
            }

            override fun onResponse(
                call: Call<ResponseData<Any>>,
                response: Response<ResponseData<Any>>
            ) {
                val res = response.body()
                when {
                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res.status == "ERROR" -> block(ResultData.message(res.message))
                    res.status == "OK" -> block(ResultData.data(res.data!!))
                }

            }
        })
}

fun verify(code: String, block: (ResultData<Any>) -> Unit) {
    api.verifyPhoneNumber(VerifyCodeUserData(localStorage.phoneNumber, code))
        .enqueue(object : Callback<ResponseData<Any>> {
            override fun onFailure(call: Call<ResponseData<Any>>, t: Throwable) {
                block(ResultData.failure(t))
            }

            override fun onResponse(
                call: Call<ResponseData<Any>>,
                response: Response<ResponseData<Any>>
            ) {
                val res = response.body()
                when {
                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res.status == "ERROR" -> block(ResultData.message(res.message))
                    res.status == "OK" -> block(ResultData.data(res.data!!))
                }

            }
        })
}

fun Fragment.showMessage(message: MessageData) {
    var text: String = ""
    message.onMessage {
        text = it
    }.onResource {
        text = getString(it)
    }.onFailure {
        text = when (it) {
            is HttpException -> "Internetga ulanishda xatolik!"
            else -> "Aniqlanmagan xatolik. Iltimos qayta urinib ko'ring."
        }
    }
//    showToast(text)
    val dialog = AlertDialog.Builder(context)
        .setTitle("Info!")
        .setMessage(text)
        .setPositiveButton("OK") { _, _ -> }
        .create()
    dialog.show()

}

fun saveData(it: String) {
    localStorage.token = it
    localStorage.remember = "1"
//    startActivity(Intent(this, ContactActivity::class.java))
//    finish()
}
