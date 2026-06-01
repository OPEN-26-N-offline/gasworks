package com.example.gasworks;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Arrays;
import java.util.List;

@Controller
public class CustomerController {

    @GetMapping("/customers")
    public String list(Model model) {
        List<CustomerInfo> customers = Arrays.asList(
            new CustomerInfo("C001", "田中 太郎", "東京都渋谷区桜丘町1-1", "ガスメーター(N型)", "前回: 125.4 m³", "要鍵開け", "未検針"),
            new CustomerInfo("C002", "株式会社 渋谷第一ビル", "東京都渋谷区道玄坂2-2", "ガスメーター(E型)", "前回: 88.2 m³", "裏口から入館", "検針済"),
            new CustomerInfo("C003", "桜丘メゾン管理組合", "東京都目黒区大橋3-3", "ガスメーター(N型)", "前回: 210.0 m³", "オートロック注意", "未検針"),
            new CustomerInfo("C004", "佐藤 美紀", "東京都渋谷区道玄坂1-5-9", "ガスメーター(N型)", "前回: 45.2 m³", "置き配注意", "未検針"),
            new CustomerInfo("C005", "有限会社 恵比寿ベーカリー", "東京都渋谷区恵比寿4-2-1", "ガスメーター(E型)", "前回: 312.8 m³", "早朝検針希望", "検針済"),
            new CustomerInfo("C006", "高橋 健一", "東京都目黒区青葉台1-10", "ガスメーター(N型)", "前回: 15.0 m³", "メーター位置裏庭", "未検針"),
            new CustomerInfo("C007", "代官山テラス自治会", "東京都渋谷区代官山町15-1", "ガスメーター(E型)", "前回: 0.0 m³", "新設メーター", "未検針"),
            new CustomerInfo("C008", "伊藤 幸子", "東京都渋谷区神南1-20", "ガスメーター(N型)", "前回: 92.4 m³", "チャイム不要", "検針済"),
            new CustomerInfo("C009", "レストラン・セゾン", "東京都渋谷区宇田川町30", "ガスメーター(E型)", "前回: 520.1 m³", "厨房内立入り", "未検針"),
            new CustomerInfo("C010", "中村 浩", "東京都目黒区駒場4-1", "ガスメーター(N型)", "前回: 68.7 m³", "門扉施錠あり", "未検針")
        );
        model.addAttribute("customers", customers);
        // resources/templates/ 内のファイル名と一致しているか確認してください
        return "customer_list"; 
    }

    /**
     * 顧客情報のDTO（本来は別ファイルで定義すべきクラス）
     */
    public static class CustomerInfo {
        private final String id;
        private final String name;
        private final String address;
        private final String meterType;
        private final String lastValue;
        private final String notes;
        private final String status;

        public CustomerInfo(String id, String name, String address, String meterType, String lastValue, String notes, String status) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.meterType = meterType;
            this.lastValue = lastValue;
            this.notes = notes;
            this.status = status;
        }

        // Getters
        public String getId() { return id; }
        public String getName() { return name; }
        public String getAddress() { return address; }
        public String getMeterType() { return meterType; }
        public String getLastValue() { return lastValue; }
        public String getNotes() { return notes; }
        public String getStatus() { return status; }
    }
}
