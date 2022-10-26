import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jumis.ItemsViewModel
import com.jumis.R
import com.jumis.Register
import com.jumis.Task


class CustomAdapter(private val mList: List<ItemsViewModel>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemClickListener: OnItemClickListener? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.textViewTareaCard.text = ItemsViewModel.textoTarea
        holder.textViewDescriptionCard.text = ItemsViewModel.textoDescipcion
        holder.textViewFechaCard.text = ItemsViewModel.textoFecha
        holder.textViewHoraCard.text = ItemsViewModel.textoHora

        holder.itemView.setOnClickListener(){
            val  intent = Intent(holder.itemView.context, Task::class.java)
            intent.putExtra("textViewTareaCard", ItemsViewModel.textoTarea)
            intent.putExtra("textViewDescriptionCard",ItemsViewModel.textoDescipcion)
            intent.putExtra("textViewFechaCard",ItemsViewModel.textoFecha)
            intent.putExtra("textViewHoraCard",ItemsViewModel.textoHora)
            holder.itemView.context.startActivity(intent)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textViewTareaCard: TextView = itemView.findViewById(R.id.textViewTareaCard)
        val textViewDescriptionCard: TextView = itemView.findViewById(R.id.textViewDescriptionCard)
        val textViewFechaCard: TextView = itemView.findViewById(R.id.textViewFechaCard)
        val textViewHoraCard: TextView = itemView.findViewById(R.id.textViewHoraCard)
    }
}
