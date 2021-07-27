package org.techtown.viewmodeltutorial

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.techtown.viewmodeltutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , View.OnClickListener {

    //나중에 값이 설정될거라고 lateinit으로 설정
    lateinit var myNumberViewModel: MyNumberViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뷰 모델 프로바이더를 통해 뷰모델 가져오기
        //라이프사이클을 가지고 있는 녀석을 넣어줄 즉 자기 자신
        //우리가 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델 가져오기

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java) //class를 가져올 때는 ::class.java형태

        //뷰모델이 가지고있는 라이브 데이터에 접근
        myNumberViewModel.currentValue.observe(this , Observer {
            //observer를 가지고 값이 변경되면 알아차리고 실행 됨
            //값이 변경 될 때마다 우리의 화면에 적용
            //this는 이걸 지켜보는 라이플 사이클 오너
            Log.d("로그","MainActivitiy - myNumberViewModel - currentValue 라이브 데이터 값 변경 : ${it}")  //it은 람다식 다시공부
            binding.numberTextview.text = it.toString() //데이터를 변경 numbertextView의 text는 값이 변경되었고 거기에 들어온 값으로 변경
        })


        //리스너 연결
        binding.plusBtn.setOnClickListener(this)    //자기 자신에 implements 했으므로 this 가능
        binding.minusBtn.setOnClickListener(this)
    }

    //클릭
    override fun onClick(view: View?) {
        val userInput = binding.userinputEdittext.text.toString().toInt()

        when(view){ //누른 버튼별로 when에서 실행
            //뷰모델이 가지고 있는 라이브데이터 값 변경
            binding.plusBtn -> myNumberViewModel.updateValue(ActionType.PLUS , userInput)
            binding.minusBtn ->myNumberViewModel.updateValue(ActionType.MINUS,userInput)
        }
    }
}