package br.edu.utfpr.trucomarker

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.trucomarker.databinding.ActivityHistoryBinding
import br.edu.utfpr.trucomarker.datas.TeamData

class HistoryActivity : AppCompatActivity() {
  private lateinit var binding: ActivityHistoryBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    binding = ActivityHistoryBinding.inflate(layoutInflater)
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.btResetHistory.setOnClickListener {
      val builder = AlertDialog.Builder(this)

      builder.setTitle("Tem certeza que deseja resetar histórico?")
        .setPositiveButton("Sim") { dialog, _ ->
          resetHistory()
          dialog.dismiss()
          Toast.makeText(
            this,
            "Histórico reiniciado com sucesso",
            Toast.LENGTH_SHORT
          ).show()
        }
        .setNegativeButton("Não") { dialog, _ ->
          dialog.dismiss()
        }
        .setCancelable(false)

      val dialog: AlertDialog = builder.create()
      dialog.show()
    }

    binding.btBack.setOnClickListener {
      finish()
    }
  }

  override fun onResume() {
    super.onResume()

    binding.tvNameTeam1.text = TeamData.team1Name
    binding.tvWinTeam1.text = TeamData.team1Wins.toString()

    binding.tvNameTeam2.text = TeamData.team2Name
    binding.tvWinTeam2.text = TeamData.team2Wins.toString()
  }

  private fun resetHistory() {
    binding.tvWinTeam1.text = "0"
    TeamData.team1Wins = 0

    binding.tvWinTeam2.text = "0"
    TeamData.team2Wins = 0
  }
}