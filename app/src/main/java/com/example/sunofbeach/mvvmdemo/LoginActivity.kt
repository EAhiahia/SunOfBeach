package com.example.sunofbeach.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.sunofbeach.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(),
    LoginPresenter.OnCheckUserNameStateResultCallback,
    LoginPresenter.OnDoLoginStateChange {

    private val loginPresenter by lazy {
        LoginPresenter()
    }

    private var isUserNameCanBeUse = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        loginBtn.setOnClickListener {
            //去进行登陆
            toLogin()
        }
        //监听内容变化
        accountInputBox.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //检查当前的账号是否注册
                loginPresenter.checkUserNameState(s.toString(), this@LoginActivity)
            }

        })
    }

    /**
     * 处理登陆逻辑
     */
    private fun toLogin() {
        //做登录的逻辑处理
        val account = accountInputBox.text.toString()
        val password = passwordInputBox.text.toString()
        if(!isUserNameCanBeUse){
            //提示用户说当前用户已被注册
            return
        }
        //异步操作，耗时
        //如果我们点击了登陆，然后关闭了界面，然后等服务器返回登陆指令的时候更新UI就会出现空指针异常
        loginPresenter.doLogin(
            account,
            password,
            this
        )

    }

    override fun onAccountFormatError() {
        loginTipsText?.text = "账号不可以为空..."

    }

    override fun onPasswordEmpty() {
        loginTipsText?.text = "密码不可以为空..."
    }

    override fun onLoading() {
        loginTipsText?.text = "登陆中..."
    }

    override fun onLoginSuccess() {
        loginTipsText?.text = "登陆成功..."
    }

    override fun onLoginFailed() {
        loginTipsText?.text = "登陆失败..."
    }

    override fun onNotExist() {
        //当前用户可用
        loginTipsText?.text = "该用户名可用..."
        this.isUserNameCanBeUse = true
    }

    override fun onExist() {
        //用户名不可用
        loginTipsText?.text = "该用户名已被注册了..."
        this.isUserNameCanBeUse = false
    }
}