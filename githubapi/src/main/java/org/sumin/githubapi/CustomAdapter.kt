package org.sumin.githubapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import org.sumin.githubapi.databinding.ItemRecyclerBinding

class CustomAdapter: RecyclerView.Adapter<Holder>() {   //리사이클러 아답터 상속받고 제네릭으로 Holder 지정
    var userList: Repository? = null        //데이터 컬렉션을 변수로 만들어놓음(첨 값이 널이니까 물음표)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {      //총 아이템 개수 구하기
        return userList?.size?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {      //실제 목록에 뿌려지는 아이템을 그려줌
        val user = userList?.get(position)      //현위치의 사용자 데이터를 유저리스트에서 가져오고
        holder.setUser(user)        //홀더에 setUser 메소드에 넘겨준 후
    }
}
class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){       //홀더의 생성자에 바인딩 전달받음, 뷰홀더 상속받아 바인딩루트에 전달
    fun setUser(user: RepositoryItem?){     //유저 데이터 설정
        user?.let{
            with(binding){
                textName.text = user.full_name
                textId.text = user.node_id
                Glide.with(imageAvatar).load(user.owner.avatar_url).into(imageAvatar)
            }
        }
    }
}