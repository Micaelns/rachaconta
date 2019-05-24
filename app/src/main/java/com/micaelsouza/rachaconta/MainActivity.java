package com.micaelsouza.rachaconta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARACAO DAS PROPRIEDADES DA TELA
    private int ProgressStatus=10;
    private SeekBar seekBar;
    private TextView txtSeekBar, vlrGarcom, vlrTotal,vlrIndividual;
    private EditText edtValor, edtNumPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVars();
    }

    public Boolean ValidaCampo(String campo){
        if(campo == null || campo.equals(""))
            return false; return true;
    }

    // PROCEDIMENTO PARA INICIALIZAR OS OBJETOS VISUAIS
    private void inicializarVars(){
        txtSeekBar = findViewById(R.id.txtSeekBarId);
        edtValor = findViewById(R.id.edtValorId);
        edtNumPessoas = findViewById(R.id.edtNumPessoasId);
        vlrGarcom = findViewById(R.id.vlrGarcomId);
        vlrTotal = findViewById(R.id.vlrTotalId);
        vlrIndividual = findViewById(R.id.vlrIndividualId);

        seekBar = findViewById(R.id.seekBarId);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ProgressStatus = progress;
                txtSeekBar.setText(String.valueOf(ProgressStatus)+"%");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {
                txtSeekBar.setText(String.valueOf(ProgressStatus)+"%");
            }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    public void calculaPercentual(View view){
        if(ValidaCampo(edtValor.getText().toString()) && ValidaCampo(edtNumPessoas.getText().toString())){
            double Valor = Double.parseDouble(edtValor.getText().toString());
            int Pessoas = Integer.parseInt(edtNumPessoas.getText().toString());
            double ValorGarcom = (Valor/100.0)*ProgressStatus;
            double ValorTotal = (Valor + ValorGarcom);
            double ValorIndividual = (ValorTotal)/Pessoas;
            vlrGarcom.setText("R$ "+ String.format("%02.2f",ValorGarcom));
            vlrTotal.setText("R$ "+String.format("%02.2f",ValorTotal));
            vlrIndividual.setText("R$ "+String.format("%02.2f",ValorIndividual));
        }else{
            Toast.makeText(getApplicationContext(), "Preencha os valores!", Toast.LENGTH_LONG).show();
        }
    }
}
