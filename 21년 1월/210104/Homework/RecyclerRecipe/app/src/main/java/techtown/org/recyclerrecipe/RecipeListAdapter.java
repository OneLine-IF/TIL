package techtown.org.recyclerrecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>{
    private final LinkedList<String> mRecipeName;
    private final LinkedList<String> mRecipeDetail;
    private LayoutInflater mInflater;
    public final static String EXTRA_NAME="com.example.android.recyclerrecipe.EXTRA.NAME";

    public RecipeListAdapter(Context context, LinkedList<String> recipeName, LinkedList<String> recipeDetail){
        mInflater = LayoutInflater.from(context);
        this.mRecipeName = (LinkedList<String>)recipeName;
        this.mRecipeDetail = (LinkedList<String>)recipeDetail;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView recipeItemName;
        public final TextView recipeItemDetail;
        final RecipeListAdapter mAdapter;
        public RecipeViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeItemName = itemView.findViewById(R.id.item_name);
            recipeItemDetail = itemView.findViewById(R.id.item_detail);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            String element = mRecipeName.get(mPosition);
            Intent intent = new Intent(view.getContext(),Recipe_detail.class);
            Bundle extra = new Bundle();
            extra.putString(EXTRA_NAME,element);
            intent.putExtras(extra);
            view.getContext().startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recipe_list,parent,false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder holder, int position) {
        String mCurrentName = mRecipeName.get(position);
        String mCurrentDetail = mRecipeDetail.get(position);
        holder.recipeItemName.setText(mCurrentName);
        holder.recipeItemDetail.setText(mCurrentDetail);
    }

    @Override
    public int getItemCount() {
        return mRecipeName.size();
    }


}
