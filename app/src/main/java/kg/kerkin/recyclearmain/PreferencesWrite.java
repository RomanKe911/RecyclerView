package kg.kerkin.recyclearmain;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
import java.util.TreeSet;

public class PreferencesWrite {

    public String PEREM_ANDROID_ID, PEREM_ANDROID_ID_ADMIN, PEREM_DB3_CONST, PEREM_DB3_BASE, PEREM_DB3_RN;
    public String PEREM_FTP_PathData, PEREM_FTP_IP_Connect;
    public String PEREM_SPINNER_NameAgent;
    public Set<String> Setting_Brends_AgentsBrends_List, Setting_Brends_HandsBrends_MultiList;
    public boolean Setting_Brends_AllBrends, Setting_Brends_AgentsBrends, Setting_Brends_HandsBrends, Setting_Brends_FirmsGroups;


    ////// информация об агенте
    public String Setting_AG_UID, Setting_AG_NAME, Setting_AG_REGION, Setting_AG_UID_REGION, Setting_AG_CENA,
            Setting_AG_SKLAD, Setting_AG_UID_SKLAD, Setting_AG_TYPE_REAL, Setting_AG_TYPE_USER;

    public String Setting_TY_Type, Setting_TY_MinSumma, Setting_TY_SkidkaMoney, Setting_TY_SkidkaNoMoney;


    public String Setting_MT_K_AG_NAME, Setting_MT_K_AG_UID, Setting_MT_K_AG_ADRESS,
            Setting_MT_K_AG_KodRN, Setting_MT_K_AG_Data, Setting_MT_K_AG_Data_WORK,
            Setting_MT_K_AG_Vrema, Setting_MT_K_AG_GPS, Setting_MT_KodUIDKodRN;

    public String Setting_DATA_WORK_DISTR, SelectMenu_forBrends;
    public boolean Setting_DATA_MailMessege;

    public String Setting_Filters_Clients, Setting_Filters_Clients_UID, Setting_Filters_DataStart, Setting_Filters_DataEND;
    public boolean Setting_FiltersSelectClients, Setting_FiltersSelectDate, StatusUpdateList, Setting_FiltersSelectGroup;

    public boolean Setting_ImageputToOld, Setting_ImageputToFiles, Setting_ImageputToSDCard;

    public String Setting_Zakaz_PEREM_NEW_DEBET_WRITE, Setting_TY_CREDIT,
            Setting_TY_DateNextUP, Setting_TY_Comment, Setting_TY_CREDITE_DATE, Setting_TY_Itogo, Setting_NameFileToFtp;

    public String Setting_TY_Sale, Setting_TY_SaleFarm, Setting_TY_SaleMinSum, Setting_TY_SaleMinSumSale, Setting_TY_SaleType;
    public int Setting_TY_SaleRandom, Setting_TY_TypeRelise;

    public String PinCodes = "8888";  /// Пин-код для входа в приложение

    public PreferencesWrite(Context context) {
        final String APP_PREFERENCES = "kg.roman.Mobile_Torgovla_preferences";
        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        PEREM_ANDROID_ID = mSettings.getString("PEREM_ANDROID_ID", "0");
        PEREM_ANDROID_ID_ADMIN = mSettings.getString("PEREM_ANDROID_ID_ADMIN", "0");
        PEREM_DB3_CONST = mSettings.getString("PEREM_DB3_CONST", "0");
        PEREM_DB3_BASE = mSettings.getString("PEREM_DB3_BASE", "0");
        PEREM_DB3_RN = mSettings.getString("PEREM_DB3_BaseRN", "0");
        PEREM_FTP_PathData = mSettings.getString("setting_ftpPathData", "0");
        PEREM_FTP_IP_Connect = mSettings.getString("setting_ftpIP", "0");
        PEREM_SPINNER_NameAgent = mSettings.getString("PEREM_ISNAME_SPINNER", "0");


        Setting_AG_UID = mSettings.getString("PEREM_AG_UID", "0");                         //чтение данных: передача кода агента (A8BA1F48-C7E1-497B-B74A-D86426684712)
        Setting_AG_NAME = mSettings.getString("PEREM_AG_NAME", "0");                       //чтение данных: передача кода агента (Имя пользователя)
        Setting_AG_REGION = mSettings.getString("PEREM_AG_REGION", "0");                   //чтение данных: маршруты для привязки к контагентам
        Setting_AG_UID_REGION = mSettings.getString("PEREM_AG_UID_REGION", "0");           //чтение данных: uid маршруты для привязки к контагентам
        Setting_AG_CENA = mSettings.getString("PEREM_AG_CENA", "0");                       //чтение данных: цены для агентов
        Setting_AG_SKLAD = mSettings.getString("PEREM_AG_SKLAD", "0");                     //чтение данных: склады для агентов
        Setting_AG_UID_SKLAD = mSettings.getString("PEREM_AG_UID_SKLAD", "0");             //чтение данных: UID склады для агентов
        Setting_AG_TYPE_REAL = mSettings.getString("PEREM_AG_TYPE_REAL", "0");             //чтение данных: выбор типа торгового агента 1-OSDO или 2-PRES
        Setting_AG_TYPE_USER = mSettings.getString("PEREM_AG_TYPE_USER", "0");             //чтение данных: тип учетной записи агент или экспедитор
        Setting_MT_KodUIDKodRN = mSettings.getString("PEREM_KOD_UID_KODRN", "0");

        Setting_DATA_WORK_DISTR = mSettings.getString("PEREM_WORK_DISTR", "0");         //чтение данных: имя папки с данными (01_WDay)
        Setting_DATA_MailMessege = mSettings.getBoolean("key_ftpMailMessege", false);   //чтение данных: настройки об уведомление оператору
        Setting_TY_Type = mSettings.getString("preference_TradeStatus", "0");             //чтение данных: путь данных на ftp
        Setting_TY_MinSumma = mSettings.getString("preference_ListMinSumTrade", "0");             //чтение данных: путь данных на ftp
        Setting_TY_SkidkaMoney = mSettings.getString("preference_ListTradeNal", "0");             //чтение данных: путь данных на ftp
        Setting_TY_SkidkaNoMoney = mSettings.getString("preference_ListTradeKons", "0");             //чтение данных: путь данных на ftp
/*        Setting_PEREM_KOD_MOBILE = mSettings.getString("PEREM_KOD_MOBILE", "0");                 //чтение данных:
                   //чтение данных: уникальный код для накладной*/


        Setting_MT_K_AG_NAME = mSettings.getString("PEREM_K_AG_NAME", "0");          //чтение данных: имя контраегнта
        Setting_MT_K_AG_UID = mSettings.getString("PEREM_K_AG_UID", "0");            //чтение данных: uid контрагента
        Setting_MT_K_AG_ADRESS = mSettings.getString("PEREM_K_AG_ADRESS", "0");      //чтение данных: адрес контрагент
        Setting_MT_K_AG_KodRN = mSettings.getString("PEREM_K_AG_KodRN", "0");         //чтение данных: код накладной
        Setting_MT_K_AG_Data = mSettings.getString("PEREM_K_AG_Data", "0");           //чтение данных: время создание н
        Setting_MT_K_AG_Data_WORK = mSettings.getString("PEREM_K_AG_Data_WORK", "0");           //чтение данных: время создание н
        Setting_MT_K_AG_Vrema = mSettings.getString("PEREM_K_AG_Vrema", "0");        //чтение данных: дата создание на
        Setting_MT_K_AG_GPS = mSettings.getString("PEREM_K_AG_GPS", "0");             //чтение данных: координаты gps



        Setting_Brends_AllBrends = mSettings.getBoolean("key_SwitchPreference_BrendsAll", false);        // Все для всех
        Setting_Brends_AgentsBrends = mSettings.getBoolean("key_SwitchPreference_BrendsAgents", false);  // Сортировка для агентов
        Setting_Brends_HandsBrends = mSettings.getBoolean("key_SwitchPreference_BrendsManual", false);   // Ручной отбор
        Setting_Brends_FirmsGroups = mSettings.getBoolean("key_SwitchPreference_BrendsFirms", false);    // Отображение по фирмам
        Setting_Brends_AgentsBrends_List = new TreeSet<>(mSettings.getStringSet("PEREM_BrendsForNomeclature", new TreeSet<>()));
        Setting_Brends_HandsBrends_MultiList = new TreeSet<>(mSettings.getStringSet("key_MultiSelectPreference_BrendsManual", new TreeSet<>()));



        Setting_FiltersSelectGroup = mSettings.getBoolean("setting_Filters_Group", false);   // Групирвока товара
        Setting_FiltersSelectClients = mSettings.getBoolean("setting_Filters_Cliets", false);   // Ручной отбор
        Setting_FiltersSelectDate = mSettings.getBoolean("setting_Filters_Date", false);       // Ручной отбор
        Setting_Filters_Clients = mSettings.getString("setting_Filters_Cliets_Name", "0");           //чтение данных: время создание н
        Setting_Filters_Clients_UID = mSettings.getString("setting_Filters_Cliets_NameUID", "0");           //чтение данных: время создание н
        Setting_Filters_DataStart = mSettings.getString("setting_Filters_DateStart", "0");           //чтение данных: время создание н
        Setting_Filters_DataEND = mSettings.getString("setting_Filters_DateEnd", "0");           //чтение данных: время создание н
        Setting_NameFileToFtp = mSettings.getString("setting_NameFileToFTP", "agent");           //чтение данных: время создание н

        Setting_ImageputToOld = mSettings.getBoolean("key_settingImageOld", false);  // Переменая номер накладной
        Setting_ImageputToFiles = mSettings.getBoolean("key_settingImagePhone", false);  // Переменая номер накладной
        Setting_ImageputToSDCard = mSettings.getBoolean("key_settingImageSDCard", false);  // Переменая номер накладной





       // Setting_Zakaz_PEREM_NEW_DEBET_WRITE = mSettings.getString("PEREM_NEW_DEBET_WRITE", "0");
       // Setting_Zakaz_PEREM_DOP_ITOGO = mSettings.getString("PEREM_NEW_DEBET_WRITE", "0");
       // Setting_Zakaz_PEREM_NEW_DEBET_WRITE = mSettings.getString("PEREM_NEW_DEBET_WRITE", "0");



        Setting_TY_Sale = mSettings.getString("preference_ListSaleStandart", "0");              // размер скидки
        Setting_TY_SaleFarm = mSettings.getString("preference_ListSaleFarm", "0");              // размер скидки
        Setting_TY_SaleRandom = mSettings.getInt("preference_SaleRandom", 0);          // размер скидки
        Setting_TY_SaleMinSum = mSettings.getString("preference_ListMinSumTrade", "0");         // размер скидки
        Setting_TY_SaleMinSumSale = mSettings.getString("preference_MinSumSale", "0");          // размер скидки
        Setting_TY_SaleType = mSettings.getString("preference_TypeSale", "standart");          // размер скидки



        Setting_TY_TypeRelise = mSettings.getInt("setting_TY_TypeRelise", 0);            // тип реализации счет-фактура или нет
        Setting_TY_DateNextUP = mSettings.getString("setting_TY_DateNextUP", "0");      // дата выгрузки заказа
        Setting_TY_Comment = mSettings.getString("setting_TY_COMMENT", "0");            // коментарии к заказу

        Setting_TY_CREDIT = mSettings.getString("setting_TY_CREDIT", "0");              // тип оплаты
        Setting_TY_CREDITE_DATE = mSettings.getString("setting_TY_CREDITE_DATE", "0");  // тип оплаты, дней консигнаций
        Setting_TY_Itogo = mSettings.getString("setting_EndZakaz_Itogo", "0");



        StatusUpdateList = mSettings.getBoolean("status_UpdateListView", false);
        SelectMenu_forBrends = mSettings.getString("sp_BREND", "null");

    }
}
