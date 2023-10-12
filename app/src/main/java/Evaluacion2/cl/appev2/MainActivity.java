package Evaluacion2.cl.appev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Evaluacion2.cl.appev2.Clases.Pedidos;

public class MainActivity extends AppCompatActivity {

    private List<Pedidos> ListPedidos = new ArrayList<Pedidos>();
    ArrayAdapter<Pedidos> arrayAdapterPedidos;

    EditText etNombre,etEstado;
    Button btAgregar;
    ListView lvPedidos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre=findViewById(R.id.etNombre);
        etEstado=findViewById(R.id.etEstado);
        btAgregar=findViewById(R.id.btAgregar);
        lvPedidos=findViewById(R.id.lvPedidos);
        inicializarFireBase();
        listarDatos();

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pedidos pedidos = new Pedidos();
                pedidos.setIdPedido(UUID.randomUUID().toString());
                pedidos.setNombre(etNombre.getText().toString());
                pedidos.setEstado(etEstado.getText().toString());
                databaseReference.child("Pedidos").child(pedidos.getIdPedido()).setValue(pedidos);


            }
        });



    }


    private void listarDatos() {
        databaseReference.child("Pedidos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListPedidos.clear();
                for (DataSnapshot objs : snapshot.getChildren()){
                    Pedidos pe =objs.getValue(Pedidos.class);
                    ListPedidos.add(pe);
                    arrayAdapterPedidos =new ArrayAdapter<Pedidos>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,ListPedidos);
                    lvPedidos.setAdapter(arrayAdapterPedidos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }

}