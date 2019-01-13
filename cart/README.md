# Shopping Cart

Alisveris sitesinin sepet sureclerini yerine getiren rest apidir.Projeyi build ettikten sonra tarayiciniz ile 'http://localhost:8090/swagger-ui.html' adresine gidip api'ye ait tüm servisleri görebilir ve bu servislere request atıp, response'larini alabilirsiniz.

# Sistemdeki Default Modeller

- Kategori Bilgileri: Sistemde Telefon ve Aksesuar adinda iki tane kategori bulunmaktadir.

- Kupon Bilgileri: 100 Tl uzeri alimlarda ekstra %10 indirim tanimli kuponumuz mevcuttur.

- Kampanya Bilgileri: 3 adet kampanya tanimlidir. Telefon kategorisinde 3 adetten fazla urun aliminda ekstra %20 indirim mevcuttur. Aksesuar kategorisinde ise 5 adet uzeri alimlarda ekstra %50 ve ayrica 5 tl indirim mevcuttur.

- Urun Bilgileri: Apple ve Telefon Kılıfı olmak uzere iki adet urun tanimlidir.


# Servisler

* http://localhost:8090/cart (POST) -> Yeni sepet olusturur. CreateShoppingCartRequest nesnesi bu request için body 'de gönderilir.

# Projede Kullanılan Teknolojiler 

- Projemiz Spring Boot Mvc projesidir.
- Loglama için Aop kullanilmistir.
- Rest Api'mizi dökümante edebilmek amaci ile swagger kullanilmistir.
- Datalarimizi mock'lamak icin Mockito kullanilmistir.
- Projenin build edilmesi icin Docker kullanilmistir.
- Junit
- Lombok
- Maven

# Projenin Build Edilmesi

Projenin İntellij idea araciliyla build islemi:

  Projeyi Github'dan bilgisayarimizi indirdikten sonra İntellij idea acilir ve File->New->Module From Existing Source tıklanir ve projenin   oldugu dizin gosterilir. Maven secilir ve next'e basilarak proje eklenmis olur. Edit configuration acilir ve Spring Boot secilir main     class olarak HangmanGameApplication.java class' inin oldugu path verilir. JRE olarakta 1.8 secilir ve run edilir.
  
Projenin Docker araciliyla build islemi:  

  Bilgisayarinizda docker kurulu ise asagidaki iki komut calistirilir.
  - docker build -f Dockerfile -t shopping-cart .
  - docker run -p 8090:8090 shopping-cart
  
#  NOT
   Sepetimizi olusturmak icin atacagimiz requestimizdeki category'nin title'i ve productName alanindaki degerlere dikkat etmemiz gerekiyor. 
   - Category'nin title degeri sistemimizde olan category title'lari ile ayni olmali.
   - productName alani ise sistemimizde olan product name'i ile ayni olmalidir.
   
   Yukaridaki iki maddenin sebebi; sistemde modellerin olusturulmasinda id degerleri kullanilmamistir. Bu sebep ile girmis oldugunuz category title ve product name alanlari ile ilgili category ve product bulunacaktir.
   Degerleri yanlis girmeniz halinde sistem kampanya ve kupon islemlerini dogru bir sekilde yapamayacaktir.

#  Ornek Request ve Response'lar

- Request 1 =>
 [
    {
      "category": {
        "parentCategory": {},
        "title": "Aksesuar"
      },
      "productAmount": 6,
      "productName": "Telefon Kılıfı",
      "productPrice": 100
    }
  ]

- Response 1 => 
{
    "totalAmountBeforeDiscounts": 600,
    "totalAmountAfterDiscounts": 265.5,
    "couponDiscount": 29.5,
    "campaignDiscount": 305,
    "deliveryCost": 7.99,
    "totalPrice": 273.49,
    "cartLineItem": [
      {
        "product": {
          "title": "Telefon Kılıfı",
          "price": 100,
          "category": {
            "title": "Aksesuar",
            "parentCategory": {}
          }
        },
        "productAmount": 6,
        "campaignDiscount": 305,
        "totalAmountBeforeDiscounts": 600,
        "totalAmountAfterDiscounts": 295
      }
    ]
  }

- Request 2 =>
[
  {
    "category": {
      "parentCategory": {},
      "title": "Aksesuar"
    },
    "productAmount": 6,
    "productName": "Telefon Kılıfı",
    "productPrice": 100
  },
  {
    "category": {
      "parentCategory": {},
      "title": "Telefon"
    },
    "productAmount": 2,
    "productName": "Apple",
    "productPrice": 50
  }
]
]

- Response 2 => 
{
  "totalAmountBeforeDiscounts": 700,
  "totalAmountAfterDiscounts": 265.5,
  "couponDiscount": 29.5,
  "campaignDiscount": 305,
  "deliveryCost": 12.99,
  "totalPrice": 278.49,
  "cartLineItem": [
    {
      "product": {
        "title": "Telefon Kılıfı",
        "price": 100,
        "category": {
          "title": "Aksesuar",
          "parentCategory": {}
        }
      },
      "productAmount": 6,
      "campaignDiscount": 305,
      "totalAmountBeforeDiscounts": 600,
      "totalAmountAfterDiscounts": 295
    },
    {
      "product": {
        "title": "Apple",
        "price": 50,
        "category": {
          "title": "Telefon",
          "parentCategory": {}
        }
      },
      "productAmount": 2,
      "campaignDiscount": 0,
      "totalAmountBeforeDiscounts": 100,
      "totalAmountAfterDiscounts": 0
    }
  ]
}

- Request 3 =>
[
  {
    "category": {
      "parentCategory": {},
      "title": "Telefon"
    },
    "productAmount": 2,
    "productName": "Apple",
    "productPrice": 50
  }
]
]
 
- Response 3 =>
 {
   "totalAmountBeforeDiscounts": 100,
   "totalAmountAfterDiscounts": 100,
   "couponDiscount": 0,
   "campaignDiscount": 0,
   "deliveryCost": 7.99,
   "totalPrice": 107.99,
   "cartLineItem": [
     {
       "product": {
         "title": "Apple",
         "price": 50,
         "category": {
           "title": "Telefon",
           "parentCategory": {}
         }
       },
       "productAmount": 2,
       "campaignDiscount": 0,
       "totalAmountBeforeDiscounts": 100,
       "totalAmountAfterDiscounts": 0
     }
   ]
 }

