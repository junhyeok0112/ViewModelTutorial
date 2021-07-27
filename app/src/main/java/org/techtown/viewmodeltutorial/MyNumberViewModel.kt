package org.techtown.viewmodeltutorial

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


enum class ActionType{
    PLUS,MINUS
}
//데이터의 변경
//뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MyNumberViewModel :ViewModel() {
    //데이터의 변경과 관련된 것을 뷰모델에서 설정

    //뮤터블 라이브 데이터 -> 변경가능
    //라이브 데이터 -> 수정 불가능 , 읽기전용

    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    //변경되지 않는 데이터를 가져올 때 이름을 _언더스코어 없이 설정
    //공개적으로 가져오는 변수는 private 이 아닌 퍼블릭으로 외부에게도 접근가능하도록 설정
    // 하지만 값을 직접 라이브데이터에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정
    val currentValue : LiveData<Int>                //그냥 LiveData인 value
        get() = _currentValue                       //private에서 가져온 값 -> 외부에서 접근 가능하도록

    // 뷰모델 생성될 때 초기값 설정
    init{
        Log.d("로그", "MyNumberViewModel - 생성자 호출 ")
        _currentValue.value = 0                             //currentValue에 있는 값 초기설정 (뮤터블이라 가능)
    }

    //뷰 모델이 가지고 있는 값 변경

    fun updateValue(actionType: ActionType , input:Int){
        //_currentValue 는 같은 class라서 접근가능
        when(actionType){
            ActionType.PLUS -> _currentValue.value = _currentValue.value?.plus(input)   //값이 있을수도 없을수도 있어서 value?
            ActionType.MINUS -> _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}