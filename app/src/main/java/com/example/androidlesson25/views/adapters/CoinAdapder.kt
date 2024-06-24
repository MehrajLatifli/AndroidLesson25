package com.example.androidlesson25.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.androidlesson25.R
import com.example.androidlesson25.databinding.ItemCoinBinding
import com.example.androidlesson25.models.responses.get.coin.CoinData
import com.example.androidlesson25.utilities.loadImageWithGlideAndResize
import com.example.androidlesson25.utilities.loadImageWithoutBindingAdapter
import com.example.androidlesson25.utilities.loadimagewithidWithoutBindingAdapter

class CoinAdapder : RecyclerView.Adapter<CoinAdapder.UserViewHolder>() {

    private val list = arrayListOf<CoinData>()

    lateinit var onClickItem: (CoinData) -> Unit

    inner class UserViewHolder(val itemBinding: ItemCoinBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        holder.itemBinding.item = item

        loadImage(holder.itemView.context, holder.itemBinding.imageView4, item.shortName)

    }

    private fun loadImage(context: Context, imageView: ImageView, shortName: String?) {
        val imageId = getImageResourceId(shortName)

        imageView.loadImageWithGlideAndResize(imageId,context)
    }

    private fun getImageResourceId(shortName: String?): Int {
        return when (shortName) {
            "Aave" -> R.drawable.aave
            "Algorand" -> R.drawable.algorand
            "Avalanche" -> R.drawable.avalanche
            "Basic Attention Token" -> R.drawable.basic_attention_token
            "Bitcoin" -> R.drawable.bitcoin
            "Bitcoin Cash" -> R.drawable.bitcoin_cash
            "BitTorrent [New]" -> R.drawable.bittorrent
            "BNB" -> R.drawable.bnb
            "Cardano" -> R.drawable.cardano
            "Chainlink" -> R.drawable.chainlink
            "Chiliz" -> R.drawable.chiliz
            "Dash" -> R.drawable.dash
            "Dogecoin" -> R.drawable.dogecoin
            "EOS" -> R.drawable.eos
            "Ethereum" -> R.drawable.ethereum
            "Ethereum Classic" -> R.drawable.ethereumclassic
            "Filecoin" -> R.drawable.filecoin
            "Harmony" -> R.drawable.harmony
            "Holo" -> R.drawable.holo
            "IOTA" -> R.drawable.iota
            "IOTBTC" -> R.drawable.iotbtc
            "Litecoin" -> R.drawable.litecoin
            "Monero" -> R.drawable.monero
            "Neo" -> R.drawable.neo
            "Polkadot" -> R.drawable.polkadot
            "Ravencoin" -> R.drawable.ravencoin
            "Solana" -> R.drawable.solana
            "Stellar" -> R.drawable.stellar
            "Tether" -> R.drawable.tether
            "Tether USDt" -> R.drawable.tether_usdt
            "Theta Network" -> R.drawable.theta_network
            "TRON" -> R.drawable.tron
            "Uniswap" -> R.drawable.uniswap
            "VeChain" -> R.drawable.vechain
            "WINkLink" -> R.drawable.winklink
            "XRP" -> R.drawable.xrp
            "Zcash" -> R.drawable.zcashpng
            else -> R.drawable.ic_launcher_background
        }
    }

    fun updateList(newList: List<CoinData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}