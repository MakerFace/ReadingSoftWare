package centerScence.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import centerScence.activity.ReadActivity;

/**
 * Created by 上官刀刀 on 2018/8/20.
 */

public class Book_Shelf_Item_ClickListener implements View.OnClickListener{
    private Context context;
    private String id;
    private String name;
    public Book_Shelf_Item_ClickListener(Context context,String id,String name)
    {
        this.context=context;
        this.id=id;
        this.name=name;
    }

    @Override
    public void onClick(View view) {
        Intent intent_to_reading=new Intent(context, ReadActivity.class);
        intent_to_reading.putExtra("id",id);
        intent_to_reading.putExtra("name",name);
        context.startActivity(intent_to_reading);
    }
}
