package com.example.tiktaktoegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tiktaktoegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var playerOf = true
    private var turnCount = 0

    private var boardStatus = Array(3) { IntArray(3) }
    private lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn1 = binding.btn1
        val btn2 = binding.btn2
        val btn3 = binding.btn3
        val btn4 = binding.btn4
        val btn5 = binding.btn5
        val btn6 = binding.btn6
        val btn7 = binding.btn7
        val btn8 = binding.btn8
        val btn9 = binding.btn9
        val resetBtn = binding.resetBtn


        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )
        for (i in board) {
            for (btn1 in i) {
                btn1.setOnClickListener(this)
            }
        }
        initializeBoardStatus()

        resetBtn.setOnClickListener {
            updateDisplay("Player X Turn")
            playerOf = true
            turnCount = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                    boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""

            }
        }
    }

    override fun onClick(view: View?) {

            when (view?.id) {
                R.id.btn1 -> {
                    updateValue(row = 0, col = 0, player = playerOf)
                }

                R.id.btn2 -> {
                    updateValue(row = 0, col = 1, player = playerOf)
                }

                R.id.btn3 -> {
                    updateValue(row = 0, col = 2, player = playerOf)
                }

                R.id.btn4 -> {
                    updateValue(row = 1, col = 0, player = playerOf)
                }

                R.id.btn5 -> {
                    updateValue(row = 1, col = 1, player = playerOf)
                }

                R.id.btn6 -> {
                    updateValue(row = 1, col = 2, player = playerOf)
                }

                R.id.btn7 -> {
                    updateValue(row = 2, col = 0, player = playerOf)
                }

                R.id.btn8 -> {
                    updateValue(row = 2, col = 1, player = playerOf)
                }

                R.id.btn9 -> {
                    updateValue(row = 2, col = 2, player = playerOf)
                }
            }
        playerOf=!playerOf
        turnCount++
        if(playerOf){
            updateDisplay("Player X Turn")
        }
        else {
            updateDisplay("Player O Turn")
        }
        if(turnCount==9){
            updateDisplay("Game Draw")
        }
        checkWinner()
    }

    private fun checkWinner() {
        // check horizontal

        for(i in 0..2){
            if( boardStatus[i][0] == boardStatus [i][1] &&  boardStatus[i][0] == boardStatus[i][2]){
                if( boardStatus[i][0]==1){
                   updateDisplay("Winner is X")
                    break
                }
                else if( boardStatus[i][0]==0){
                    updateDisplay("Winner is O")
                    break
                }
            }

        }
        // check Vertical
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if( boardStatus[0][i]==1){
                    updateDisplay("Winner is X")
                    break
                }
                else if( boardStatus[0][i]==0){
                    updateDisplay("Winner is O")
                    break
                }
            }
        }

        // first cross

        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if( boardStatus[0][0]==1){
                updateDisplay("Winner is X")

            }
            else if( boardStatus[0][0]==0){
                updateDisplay("Winner is O")
            }
        }
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]){
            if( boardStatus[0][2]==1){
                updateDisplay("Winner is X")

            }
            else if( boardStatus[0][2]==0){
                updateDisplay("Winner is O")
            }
        }
    }
    private fun disableBtn(){
        for (i in board) {
            for (btn1 in i) {
                btn1.isEnabled=false
            }
        }
    }

    private fun updateDisplay(text: String) {
        val tv=binding.displayTV
        tv.text=text
        if(text.contains("Winner")){
            disableBtn()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text=if (player) "X" else "O"
        val value =if(player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col]=value
    }
}