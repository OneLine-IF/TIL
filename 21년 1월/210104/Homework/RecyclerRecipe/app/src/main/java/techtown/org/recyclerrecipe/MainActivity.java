package techtown.org.recyclerrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mRecipeNames = new LinkedList<String>();
    private final LinkedList<String> mRecipeDetail = new LinkedList<String>();
    private String [] contents_name = {"Chocolate Mint Bars",
            "Blueberry Cupcakes",
            "Fudge Walnut Brownies",
            "Lemon Cake",
            "Blueberry Peach Cobbler",
            "Texas Sheet Cake",
            "Espresso Crinkles",
            "Chocolate Cherry Cookies",
            "Vanilla Cheesecake",
            "Tiramisu",
            "Carrot Cake",
            "Blueberry Ice Cream"};
    private String [] contents_detail = {"You'll love these if you're a big fan of thin chocolate mint Girl Scout cookies. The dense base layer is a rich fudgy brownie.",
            "Lemon and blueberry flavors give these cupcakes a great taste. Blueberries or fresh, edible flowers make an easy, pretty decoration.",
            "These brownies are rich in cocoa, melted chocolate and chocolate chunks.",
            "This lemon cake recipe trims the fat and calories while still retaining the moist lemony quality.",
    "Use fresh blueberries and peaches that aren't super ripe for this cobbler recipe so they'll hold their shape when cooked.",
    "This class Texas sheet cake recipe features a homemade chocolate cake layer topped with chocolate frosting and chopped toasted pecans.  Its rectangular shape makes this cake perfect for serving to a crowd.",
    "These cookies are flavored with espresso.",
    "Chocolate and cherry flavours mixed together. Using bittersweet chocolate, the sugar is reduced and the cookies get a deeper flavor.",
    "This cheesecake comes with cherry toppings.",
    "Easy and delicious recipe to make real tiramisu.",
    "Moist, light, fluffy, and low calorie carrot cake recipe.",
    "Cool off on a hot day with a big bowl of creamy homemade ice cream. Perfect for entertaining, this five-star recipe makes enough for a crowd."};
    private RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < contents_name.length; i++){
            mRecipeNames.add(i, String.valueOf(contents_name));
        }
        for(int i = 0; i < contents_detail.length;i++){
            mRecipeDetail.add(i, String.valueOf(contents_detail));
        }

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new RecipeListAdapter(this,mRecipeNames,mRecipeDetail);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}