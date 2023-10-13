package com.example.week4_inclass;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    Spinner spinnerPage;
    Button btnGen;
    EditText edtGen;
    TextView txtMsg;
    ListView listView;

    public String[] familyName={
            "Nguyễn", "Nguyễn", "Nguyễn", "Nguyễn", "Nguyễn", "Nguyễn",
            "Phạm","Đinh","Hoàng","Phan","Huỳnh","Thái", "Tô", "Trịnh",
            "Lý", "Lê", "Trần", "Bùi", "Dương", "Trang", "Tống", "Giang", "Tôn",
            "Vũ", "Võ", "Trương", "Đặng", "Đỗ", "Ngô", "Hồ", "La", "Trác", "Tạ", "Bạch",
            "Hà", "Bành", "Bế", "Cù", "Văn", "Vương", "Lương", "Khuất", "Phùng", "Tăng",
            "Nhan", "Nhâm", "Lỗ", "Lưu", "Lại", "Cao", "Ninh", "Mai"
    };
    public int countFamily = familyName.length;
    public String[] givenNameMale={
            "An", "Ân", "Anh", "Bảo", "Bá", "Bình", "Bằng", "Bách", "Chung", "Chánh", "Cảnh", "Cao", "Cường", "Châu", "Đình",
            "Dương", "Duy", "Đạt", "Đan", "Đăng", "Đức", "Gia", "Giang", "Giáp", "Huy", "Hoàng", "Hòa", "Hà", "Hiếu", "Hào", "Hậu",
            "Hoài", "Hữu", "Hưng", "Khoa", "Khôi", "Khang", "Khương", "Kiên", "Khánh", "Khải", "Kiệt", "Kha", "Long", "Lộc", "Lam", "Lâm", "Linh", "Mạnh",
            "Minh", "Nhân", "Nhàn", "Ngọc", "Nhật", "Nhựt", "Nam", "Nhã", "Nghĩa", "Nhất", "Phương", "Phát",
            "Phúc", "Phước", "Phú", "Quy", "Quý",  "Quang", "Quảng", "Quốc", "Quyền", "Quân", "Sang", "Sâm", "Sa", "Thế", "Thiên",
            "Thảo", "Thi", "Thuần", "Tuấn", "Tuân", "Tín", "Thái", "Tâm", "Trọng", "Trung", "Trí", "Thanh", "Thành", "Thịnh", "Trường", "Tường", "Thiên",
            "Uy", "Văn", "Vũ", "Vinh", "Việt", "Xuân"
    };
    public int countMale = givenNameMale.length;
    public String[] givenNameFemale={
            "An", "Ân", "Ái", "Anh", "Ánh", "Bảo", "Bình", "Châu", "Chi", "Diễm", "Dương", "Dung", "Diệp", "Đào", "Đoan", "Đan", "Gia", "Giang",
            "Hồng", "Hường", "Huệ", "Hòa", "Hoàng", "Hương", "Hậu", "Hân", "Hạnh", "Hoa", "Hoài", "Khánh", "Khương", "Kim",
            "Luyến", "Liên", "Lan", "Lam", "Loan", "Minh", "Mai", "Mẫn", "Mỹ", "Ngân", "Nguyên", "Nữ", "Nhã", "Nhung", "Như", "Nhi",
            "Nương", "Ngọc", "Nghi", "Oanh", "Phương", "Phúc", "Phụng", "Phấn", "Quyên", "Quỳnh", "Quý", "Sương", "San",
            "Thúy", "Thương", "Thảo", "Thi", "Trúc", "Thư", "Trinh", "Tâm", "Thái", "Tiên", "Uyên", "Vy", "Xuân", "Ý", "Yên", "Yến"
    };
    public int countFemale = givenNameFemale.length;
    Integer[] icons={R.drawable.icon_teamwork_1,R.drawable.icon_teamwork_2,R.drawable.icon_teamwork_3,R.drawable.icon_teamwork_4,R.drawable.icon_teamwork_5,R.drawable.actor_1,R.drawable.actor_2,R.drawable.actor_3,R.drawable.actor_4,R.drawable.actor_5,R.drawable.actor_6,R.drawable.actor_7,R.drawable.actor_9};
    String[] names;
    String[] phones;
    Integer[] thumbnails;
    String[] pages;
    int rngNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        listView = (ListView) findViewById(R.id.listView);
        spinnerPage = (Spinner) findViewById(R.id.spinnerPage);
        btnGen = (Button) findViewById(R.id.btnGen);
        edtGen = (EditText) findViewById(R.id.edtGen);


        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtGen.getText().toString().isEmpty()) {
                    String txtGen = edtGen.getText().toString();
                    try {
                        rngNum = Integer.valueOf(txtGen);
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(getApplication().getBaseContext(), "Invalid number!", Toast.LENGTH_SHORT).show();
                    }

                    generatePages(rngNum);
                    generateNames(rngNum);
                    generatePhones(rngNum);
                    generateIcons(rngNum);

                    ArrayAdapter<String> adapterPage = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, pages);
                    spinnerPage.setAdapter(adapterPage);
                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(MainActivity.this, "None", duration).show();
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtMsg.setText("You choose: " + names[position]);
            }
        });

        spinnerPage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String[] namesPage;
                    String[] phonesPage;
                    Integer[] thumbnailsPage;
                    if (names.length - position * 5 >= 5) {
                        namesPage = new String[5];
                        phonesPage = new String[5];
                        thumbnailsPage = new Integer[5];
                    } else {
                        namesPage = new String[names.length - position * 5];
                        phonesPage = new String[names.length - position * 5];
                        thumbnailsPage = new Integer[names.length - position * 5];
                    }

                    int index = 0;

                    for (int i = position * 5; i < (position + 1) * 5; i++) {
                        if (i < names.length) {
                            namesPage[index] = names[i];
                            phonesPage[index] = phones[i];
                            thumbnailsPage[index] = thumbnails[i];
                        } else {
                            break;
                        }
                        index++;
                    }

                    CustomIconLabelAdapter adapterContact = new CustomIconLabelAdapter(MainActivity.this, R.layout.custom_row_icon_label, namesPage, phonesPage, thumbnailsPage);
                    listView.setAdapter(adapterContact);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private String[] generatePages(int rngNum) {
        int numPage;
        if (rngNum%5==0) {
            numPage=rngNum/5;
        }
        else {
            numPage=rngNum/5+1;
        }
        pages=new String[numPage];
        for (int i=0;i<numPage;i++) {
            pages[i]=String.valueOf(i+1);
        }
        return pages;
    }
    private Integer[] generateIcons(int rngNum) {

        thumbnails=new Integer[rngNum];
        for (int i=0;i<rngNum;i++) {
            Random rng=new Random();
            int length=icons.length;
            Integer icon=icons[rng.nextInt(length)];
            thumbnails[i]=icon;
        }
        return thumbnails;
    }

    private String[] generatePhones(int rngNum) {

        phones=new String[rngNum];
        for (int i=0;i<rngNum;i++) {
            String phone="0";

            Random rng=new Random();

            for (int j=0;j<3;j++) {
                int num=rng.nextInt(9)+1;
                phone=phone+String.valueOf(num);
            }

            for (int j=3;j<9;j++){
                int num=rng.nextInt(10);
                phone=phone+String.valueOf(num);
            }

            phones[i]=phone;
        }
        return phones;
    }

    private String[] generateNames(int n) {
        //generate family name (tên họ)
        names = new String[n];
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
}

