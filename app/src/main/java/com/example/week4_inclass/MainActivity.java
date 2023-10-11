package com.example.week4_inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    int n;
    TextView txtMsg;
    ListView listView;
    String[] names;
    String[] phones={"0359935724","098798787","0824417567","0357456282","0567843211"};
    Integer[] thumbnails={R.drawable.icon_teamwork_1,R.drawable.icon_teamwork_2,R.drawable.icon_teamwork_3,R.drawable.icon_teamwork_4,R.drawable.icon_teamwork_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        listView=findViewById(R.id.listView);

        n = 5;
        names = generateName(n);

        CustomIconLabelAdapter adapter=new CustomIconLabelAdapter(this,R.layout.custom_row_icon_label,names,phones,thumbnails);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtMsg.setText("You choose: "+ names[position]);
            }
        });
    }
    public String[] generateName(int n) {
        //generate family name (tên họ)
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            String temp = "";
            //random family name (tên họ)
            int random = (int) Math.floor(Math.random() * (countFamily));
            temp = temp + familyName[random] + " ";
            //random nam/nữ
            random = (int) Math.floor(Math.random() * 2);
            if (random == 0) { // nam
                //"VĂN"
                random = (int) Math.floor(Math.random() * 3);
                if (random == 0) temp += "Văn ";
                //
                random = (int) Math.floor(Math.random() * (countMale));
                temp += givenNameMale[random];
                //thêm 1 chữ vào tên nữa (?)
                random = (int) Math.floor(Math.random() * 3);
                if (random == 0) {
                    random = (int) Math.floor(Math.random() * (countMale));
                    temp += " ";
                    temp += givenNameMale[random];
                }
            }
            else { // nữ
                //"THỊ"
                random = (int) Math.floor(Math.random() * 3);
                if (random == 0) temp += "Thị ";
                //
                random = (int) Math.floor(Math.random() * (countFemale));
                temp += givenNameFemale[random];
                //thêm 1 chữ vào tên nữa (?)
                random = (int) Math.floor(Math.random() * 3);
                if (random == 0) {
                    random = (int) Math.floor(Math.random() * (countFemale));
                    temp += " ";
                    temp += givenNameFemale[random];
                }
            }
            //thông tin vào array
            names[i] = temp;
        }
        return names;
    }
    public static final String[] familyName={
            "Nguyễn", "Nguyễn", "Nguyễn", "Nguyễn", "Nguyễn", "Nguyễn",
            "Phạm","Đinh","Hoàng","Phan","Huỳnh","Thái", "Tô", "Trịnh",
            "Lý", "Lê", "Trần", "Bùi", "Dương", "Trang", "Tống", "Giang", "Tôn",
            "Vũ", "Võ", "Trương", "Đặng", "Đỗ", "Ngô", "Hồ", "La", "Trác", "Tạ", "Bạch",
            "Hà", "Bành", "Bế", "Cù", "Văn", "Vương", "Lương", "Khuất", "Phùng", "Tăng",
            "Nhan", "Nhâm", "Lỗ", "Lưu", "Lại", "Cao", "Ninh", "Mai"
    };
    public static final int countFamily = familyName.length;
    public static final String[] givenNameMale={
            "An", "Ân", "Anh", "Bảo", "Bá", "Bình", "Bằng", "Bách", "Chung", "Chánh", "Cảnh", "Cao", "Cường", "Châu", "Đình",
            "Dương", "Duy", "Đạt", "Đan", "Đăng", "Đức", "Gia", "Giang", "Giáp", "Huy", "Hoàng", "Hòa", "Hà", "Hiếu", "Hào", "Hậu",
            "Hoài", "Hữu", "Hưng", "Khoa", "Khôi", "Khang", "Khương", "Kiên", "Khánh", "Khải", "Kiệt", "Kha", "Long", "Lộc", "Lam", "Lâm", "Linh", "Mạnh",
            "Minh", "Nhân", "Nhàn", "Ngọc", "Nhật", "Nhựt", "Nam", "Nhã", "Nghĩa", "Nhất", "Phương", "Phát",
            "Phúc", "Phước", "Phú", "Quy", "Quý",  "Quang", "Quảng", "Quốc", "Quyền", "Quân", "Sang", "Sâm", "Sa", "Thế", "Thiên",
            "Thảo", "Thi", "Thuần", "Tuấn", "Tuân", "Tín", "Thái", "Tâm", "Trọng", "Trung", "Trí", "Thanh", "Thành", "Thịnh", "Trường", "Tường", "Thiên",
            "Uy", "Văn", "Vũ", "Vinh", "Việt", "Xuân"
    };
    public static final int countMale = givenNameMale.length;
    public static final String[] givenNameFemale={
            "An", "Ân", "Ái", "Anh", "Ánh", "Bảo", "Bình", "Châu", "Chi", "Diễm", "Dương", "Dung", "Diệp", "Đào", "Đoan", "Đan", "Gia", "Giang",
            "Hồng", "Hường", "Huệ", "Hòa", "Hoàng", "Hương", "Hậu", "Hân", "Hạnh", "Hoa", "Hoài", "Khánh", "Khương", "Kim",
            "Luyến", "Liên", "Lan", "Lam", "Loan", "Minh", "Mai", "Mẫn", "Mỹ", "Ngân", "Nguyên", "Nữ", "Nhã", "Nhung", "Như", "Nhi",
            "Nương", "Ngọc", "Nghi", "Oanh", "Phương", "Phúc", "Phụng", "Phấn", "Quyên", "Quỳnh", "Quý", "Sương", "San",
            "Thúy", "Thương", "Thảo", "Thi", "Trúc", "Thư", "Trinh", "Tâm", "Thái", "Tiên", "Uyên", "Vy", "Xuân", "Ý", "Yên", "Yến"
    };
    public static final int countFemale = givenNameFemale.length;
    //add components
}

