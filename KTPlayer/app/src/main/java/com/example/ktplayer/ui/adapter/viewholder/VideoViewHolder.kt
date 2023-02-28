package com.example.ktplayer.ui.adapter.viewholder

import android.text.format.DateUtils
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktplayer.R
import com.example.ktplayer.databinding.VideoItemBinding
import com.example.ktplayer.model.Video
import com.example.ktplayer.ui.adapter.VideoListener

class VideoViewHolder(
    private val binding: VideoItemBinding,
    private val videoListener: VideoListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(video: Video) {
        binding.txtName.text = video.name
        binding.txtDuration.text =
            DateUtils.formatElapsedTime(video.duration / 1000)
                .toString()
        Glide.with(binding.root.context).load(video.path)
            .into(binding.imgVideo)

        binding.root.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                videoListener.onItemClicked(
                    position,
                    video
                )
            }
        }
        binding.btnMoreOption.setOnClickListener { v ->
            val context = binding.root.context
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val popupMenu = PopupMenu(context, v)
                popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                popupMenu.show()
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.details -> videoListener.showDetailsClicked(video)
                        R.id.share -> videoListener.shareVideoClicked(video)
                        R.id.delete -> videoListener.onItemDeleteClicked(position)
                    }
                    true
                }
            }
        }
    }
}