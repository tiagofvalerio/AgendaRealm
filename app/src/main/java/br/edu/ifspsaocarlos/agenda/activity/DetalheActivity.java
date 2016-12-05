package br.edu.ifspsaocarlos.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifspsaocarlos.agenda.data.ContatoDAORealm;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;
import io.realm.Realm;


public class DetalheActivity extends AppCompatActivity {
    private Contato c;
    private ContatoDAORealm cDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("contato"))
        {
            this.c = (Contato) getIntent().getParcelableExtra("contato");
            EditText nameText = (EditText)findViewById(R.id.editText1);
            nameText.setText(c.getNome());
            EditText foneText = (EditText)findViewById(R.id.editText2);
            foneText.setText(c.getFone());
            EditText foneAdicionalText = (EditText)findViewById(R.id.et_fone_adicional);
            foneAdicionalText.setText(c.getFoneAdicional());
            EditText emailText = (EditText)findViewById(R.id.editText3);
            emailText.setText(c.getEmail());
            EditText aniversarioText = (EditText)findViewById(R.id.et_aniversario);
            aniversarioText.setText(c.getDataAniversario());
            int pos =c.getNome().indexOf(" ");
            if (pos==-1)
                pos=c.getNome().length();
            setTitle(c.getNome().substring(0,pos));
        }
        cDAO = new ContatoDAORealm(Realm.getDefaultInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        if (!getIntent().hasExtra("contato"))
        {
            MenuItem item = menu.findItem(R.id.delContato);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarContato:
                salvar();
                return true;
            case R.id.delContato:
                apagar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void apagar()
    {
        cDAO.apagaContato(c);
        Intent resultIntent = new Intent();
        setResult(3,resultIntent);
        finish();
    }

    public void salvar()
    {
        String name = ((EditText) findViewById(R.id.editText1)).getText().toString();
        String fone = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String email = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String foneAdicional = ((EditText) findViewById(R.id.et_fone_adicional)).getText().toString();
        String dataAniversario = ((EditText) findViewById(R.id.et_aniversario)).getText().toString();
        if (c==null)
        {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            Long id = Long.parseLong(ft.format(dNow));

            c = new Contato();
            c.setId(id);
            c.setNome(name);
            c.setFone(fone);
            c.setEmail(email);
            c.setFoneAdicional(foneAdicional);
            c.setDataAniversario(dataAniversario);
            cDAO.insereContato(c);

        }
        else
        {
            c.setNome(name);
            c.setFone(fone);
            c.setEmail(email);
            c.setFoneAdicional(foneAdicional);
            c.setDataAniversario(dataAniversario);
            cDAO.atualizaContato(c);

        }
        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}

