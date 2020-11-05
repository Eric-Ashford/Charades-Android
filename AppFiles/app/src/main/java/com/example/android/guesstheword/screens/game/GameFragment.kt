/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 * Lambda expression is a anounymous function that isn't declared, but is passed immediately as an expression
 **/
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        //Attaching Observer to out gameFinished variable
        viewModel.gameFinished.observe(viewLifecycleOwner, Observer{ hasFinished ->
            if(hasFinished) gameFinished()
        })

        //Setting the ViewModel for DataBinding
        binding.gameViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

        /**Attaching a Observer to our Score - No longer needed since we reference LiveData in our xml**/
        //viewModel.score.observe(viewLifecycleOwner, Observer{ newScore ->
        //    binding.scoreText.text = newScore.toString()
        //})

        /**Attaching a Observer to our words - Dont need due to adding LiveData to databinding**/
        /*
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })
        */

        /**Setting on click commands for our buttons - No longer needed since we databinded the gameViewModel**/
        //binding.correctButton.setOnClickListener { onCorrect() }
        //binding.skipButton.setOnClickListener { onSkip() }
        //binding.endGameButton.setOnClickListener{   onEndGame() }
    }


    private fun gameFinished(){
        Toast.makeText(activity, "Game has just finished!", Toast.LENGTH_SHORT)
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishedComplete()
    }

    /** Methods for updating the UI - No longer needed since we are using a Live data Observer**/
    /*private fun updateWordText() {
        binding.wordText.text = viewModel.word.value
    }

    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.value.toString()
    }
    */


    /** Methods for buttons presses - No longer needed since we databinded the gameViewModel **/
    /*private fun onSkip() {
        viewModel.onSkip()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
    }

    private fun onEndGame(){
        gameFinished()
    }
    */

}
