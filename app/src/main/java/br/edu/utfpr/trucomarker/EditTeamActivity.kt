package br.edu.utfpr.trucomarker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.trucomarker.databinding.ActivityEditTeamBinding
import br.edu.utfpr.trucomarker.datas.TeamData

class EditTeamActivity : AppCompatActivity() {
  private lateinit var binding: ActivityEditTeamBinding


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityEditTeamBinding.inflate(layoutInflater)

    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.etNameTeam1.setText(TeamData.team1Name)
    binding.etNameTeam2.setText(TeamData.team2Name)

    binding.btSave.setOnClickListener {
      val team1Name = binding.etNameTeam1.text.toString().trim()
      val team2Name = binding.etNameTeam2.text.toString().trim()
      var hasError = false

      binding.tilNameTeam1.error = null
      binding.tilNameTeam2.error = null

      if (team1Name.isEmpty()) {
        binding.tilNameTeam1.error = "Informe o nome da equipe 1"
        hasError = true
      }

      if (team2Name.isEmpty()) {
        binding.tilNameTeam2.error = "Informe o nome da equipe 2"
        hasError = true
      }

      if (hasError) {
        return@setOnClickListener
      }

      TeamData.team1Name = binding.etNameTeam1.text.toString()
      TeamData.team2Name = binding.etNameTeam2.text.toString()
      finish()
    }

    binding.btCancel.setOnClickListener {
      finish()
    }
  }
}