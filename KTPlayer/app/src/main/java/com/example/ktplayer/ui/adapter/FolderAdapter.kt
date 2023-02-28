package com.example.ktplayer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ktplayer.databinding.FolderItemBinding
import com.example.ktplayer.model.Folder
import com.example.ktplayer.ui.adapter.viewholder.FolderViewHolder

class FolderAdapter(private val listener: FolderListener) :
    ListAdapter<Folder, FolderViewHolder>(FolderDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val binding = FolderItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FolderViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class FolderDiffCallback : DiffUtil.ItemCallback<Folder>() {
        override fun areItemsTheSame(oldItem: Folder, newItem: Folder) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Folder, newItem: Folder) = oldItem == newItem
    }
}

interface FolderListener {
    fun onFolderClicked(position: Int, folder: Folder)
}