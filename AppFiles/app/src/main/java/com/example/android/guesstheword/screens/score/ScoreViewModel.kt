package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore:Int) : ViewModel(){
    //The Final Score
    private val _score =  MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //Play again properties
    private val _restartGame = MutableLiveData<Boolean>()
    val restartGame: LiveData<Boolean>
        get() = _restartGame


    init{
        _score.value = finalScore
    }

    fun onPlayAgain(){
        _restartGame.value = true
    }

    fun onPlayAgainComplete(){
        _restartGame.value = false
    }
}