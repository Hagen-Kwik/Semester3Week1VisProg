package adapter

import Interface.CardListener
import Model.animal
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.refreshmentexercisesemester3week1.R
import com.example.refreshmentexercisesemester3week1.home
import com.example.refreshmentexercisesemester3week1.masukin_data_activity
import kotlinx.android.synthetic.main.cardview_foranimal.view.*

class RecyclerViewAdapter(val listanimal: ArrayList<animal>, val cardListener: CardListener):
    RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.viewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val view = layoutInflator.inflate(R.layout.cardview_foranimal, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.viewHolder, position: Int) {
        holder.setData(listanimal[position])
    }

    override fun getItemCount(): Int {
        return listanimal.size
    }


    class viewHolder(itemView: View, val cardListener: CardListener) :
        RecyclerView.ViewHolder(itemView) {

        val edit = itemView.editbutton
        val delete = itemView.delete

        val name_card = itemView.namee
        val type_card = itemView.typee
        val age_card = itemView.agee
        val picture_card = itemView.imageforcardview


        fun setData(data: animal) {



            name_card.text = data.nama
            type_card.text = data.jenis
            age_card.text = data.usia.toString()
            if (data.imageuri != null) {
                picture_card.setImageURI(data.imageuri)
            }

            edit.setOnClickListener {
                cardListener.editCLICKED(adapterPosition)
            }

            delete.setOnClickListener{
                cardListener.deleteCLICKED(adapterPosition)
            }

        }
    }
}

