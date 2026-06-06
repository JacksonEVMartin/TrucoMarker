package br.edu.utfpr.trucomarker

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.trucomarker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)

    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.btTeam1Add1.setOnClickListener {
      addScore(1, 1)
    }
    binding.btTeam1Add3.setOnClickListener {
      addScore(1, 3)
    }
    binding.btTeam1Add6.setOnClickListener {
      addScore(1, 6)
    }
    binding.btTeam1Add9.setOnClickListener {
      addScore(1, 9)
    }
    binding.btTeam1Add12.setOnClickListener {
      addScore(1, 12)
    }

    binding.btTeam2Add1.setOnClickListener {
      addScore(2, 1)
    }
    binding.btTeam2Add3.setOnClickListener {
      addScore(2, 3)
    }
    binding.btTeam2Add6.setOnClickListener {
      addScore(2, 6)
    }
    binding.btTeam2Add9.setOnClickListener {
      addScore(2, 9)
    }
    binding.btTeam2Add12.setOnClickListener {
      addScore(2, 12)
    }

    binding.btTeam1Subtract.setOnClickListener {
      subtractScore(1)
    }
    binding.btTeam2Subtract.setOnClickListener {
      subtractScore(2)
    }
  }

  private fun addScore(team: Int, scoreAdd: Int) {
    val scoreTeam1 = binding.tvScoreTeam1.text.toString().toInt()
    val scoreTeam2 = binding.tvScoreTeam2.text.toString().toInt()
    var newScore = 0
    var winner = binding.tvNameTeam1.text.toString()
    var loser = binding.tvNameTeam2.text.toString()

    if (team == 1) {
      newScore = scoreTeam1 + scoreAdd
      binding.tvScoreTeam1.text = newScore.toString()

    } else {
      newScore = scoreTeam2 + scoreAdd
      binding.tvScoreTeam2.text = newScore.toString()
      winner = binding.tvNameTeam2.text.toString()
      loser = binding.tvNameTeam1.text.toString()
    }

    if (newScore >= 12) {
      alertVictory(
        winner,
        loser,
      )
    }
  }

  private fun subtractScore(team: Int) {
    val scoreTeam1 = binding.tvScoreTeam1.text.toString().toInt()
    val scoreTeam2 = binding.tvScoreTeam2.text.toString().toInt()
    var newScore = 0

    if (team == 1) {
      newScore = scoreTeam1 - 1
    } else {
      newScore = scoreTeam2 - 1
    }

    if (newScore < 0) {
      binding.tvScoreTeam1.text = "0"
      return
    }

    if (team == 1) {
      binding.tvScoreTeam1.text = newScore.toString()
    } else {
      binding.tvScoreTeam2.text = newScore.toString()
    }

  }

  private fun resetScoreTeams() {
    // adicionar logica para salvar historico
    binding.tvScoreTeam1.text = "0"
    binding.tvScoreTeam2.text = "0"
  }

  private fun alertVictory(winningTeam: String, losingTeam: String) {
    val builder = AlertDialog.Builder(this)

    builder.setTitle("Temos um vencedor")
      .setMessage("O Time ${winningTeam} venceu o ${losingTeam}")
      .setPositiveButton("Fechar") { dialog, which ->
        resetScoreTeams()
        dialog.dismiss()
      }
      .setCancelable(false)

    val dialog: AlertDialog = builder.create()
    dialog.show()
  }
}