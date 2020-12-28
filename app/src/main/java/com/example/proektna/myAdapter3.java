package com.example.proektna;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class myAdapter3 extends RecyclerView.Adapter<myAdapter3.ViewHolder> {
    private List<ReservationModel> myList;
    private int rowLayout;
    private Context context;


    public class Item {
        public String picName;
        public int drawable;

        public Item (String picName, int drawable) {
            this.drawable = drawable;
            this.picName = picName;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView my_user_id;
        public TextView my_time_slot;
        public TextView my_date;
        public Button my_delete_reservation;
        public ImageView qr_code;
        Bitmap bitmap;

        public ViewHolder(View view) {
            super(view);
            my_user_id = (TextView) itemView.findViewById(R.id.my_user_id);
            my_time_slot = (TextView) itemView.findViewById(R.id.my_time_slot);
            my_date = (TextView) itemView.findViewById(R.id.my_date);
            my_delete_reservation = (Button) itemView.findViewById(R.id.my_delete_reservation);
            qr_code = (ImageView) itemView.findViewById(R.id.qr_code);
        }
    }

    public myAdapter3(List<ReservationModel> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ReservationModel reservationModel = myList.get(i);

        viewHolder.my_user_id.setText(String.valueOf(reservationModel.getParkingID()));
        viewHolder.my_time_slot.setText(reservationModel.getTimeSlot());
        viewHolder.my_date.setText(reservationModel.getDate());

        DBHelper dbHelper = new DBHelper(this.context);

        String uniqueQR = String.valueOf(reservationModel.getReservationID()) + reservationModel.getTimeSlot() + reservationModel.getDate();
        QRGEncoder qrgEncoder = new QRGEncoder(uniqueQR, null, QRGContents.Type.TEXT, 300);
        try {
            viewHolder.bitmap = qrgEncoder.encodeAsBitmap();
            viewHolder.qr_code.setImageBitmap(viewHolder.bitmap);
        } catch (WriterException e) {
            Log.v("mystupidtag", e.toString());
        }




        viewHolder.my_delete_reservation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.deleteReservation(reservationModel.getReservationID());
                Intent intent = new Intent(v.getContext(), MyReservations.class);
                v.getContext().startActivity(intent);
            }
        });
//        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) v;
//                Toast.makeText(context, tv.getText(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), ReservationForm.class);
//                // NEW CHANGE
//                //intent.putExtra("city_name", myList.get(i));
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("city_name", entry);
//                editor.commit();
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}