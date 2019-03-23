package no.hvl.dat109.prosjekt.utilities;

public class UrlPaths {
    //Userpages
    public final static String BASE_USERPAGES_HTML = "/userpages",
            BEDRIFT_HTML = BASE_USERPAGES_HTML + "/bedrift",
            KONTAKT_HTML = BASE_USERPAGES_HTML + "/kontakt",
            BASE_KONTAKT = "/kontakt",
            STAND_HTML = BASE_USERPAGES_HTML + "/stand",
            MINE_STEMMER_HTML = BASE_USERPAGES_HTML + "/mine_stemmer",
            TAKK_FOR_STEMME_HTML = BASE_USERPAGES_HTML + "/takk_for_stemme";

    //Bedrift
    public final static String BEDRIFT_BASE = "/bedrift",
            BEDRIFT_WITH_ID = BEDRIFT_BASE + "/{id}",
            ADD_BEDRIFT = BEDRIFT_BASE + "/add";

    //Arrangement
    public final static String arrangementBase = "/arrangement",
            ARRANGEMENT_WITH_ID = arrangementBase + "/{id}",
            ARRANGEMNT_DELTAGELSE_HTML = "deltagelser";

    //Prosjekt
    public final static String
            baseProsjekt = "/prosjekt",
            PROJECT_WITH_ID = baseProsjekt + "/{id}",
            ADD_PROSJEKT = baseProsjekt + "/add",
            PROSJEKT_STATISTIKK = PROJECT_WITH_ID + "/statistikk",
            SHOW_QR = PROJECT_WITH_ID + "/qr",
            CREATE_QR_IMAGE = SHOW_QR + "/create",
            REMOVE_PROSJEKT = PROJECT_WITH_ID + "/remove",
            UPLOAD_PROSJEKT_IMAGES = PROJECT_WITH_ID + "/upload",
            prosjekter = "/prosjekter",
            REMOVE_ALL_PROSJEKT_FILES = prosjekter + "/apocalypse";

    //Dashboard
    public final static String DASHBOARD = "/dashboard",
            PROSJEKT_DASHBOARD = DASHBOARD + "/{id}";

    //Stand pages
    public final static String baseStandPages = "/standpages",
            STAND_STATISTIKK_HTML = baseStandPages + "/stand_statistikk",
            STAND_DASHBOARD_HTML = baseStandPages + "/dashboard",
            STAND_QR_HTML = baseStandPages + "/qrkode";
    //Api
    public final static String API_BASE = "/api",
            API_PROSJEKT = API_BASE + "/prosjekt/{id}",
            STEM_HTML = API_PROSJEKT + "/stemmer",
            API_PROSJEKTER = API_BASE + "/prosjekter",
            API_PROSJEKTER_STEMMER = API_PROSJEKTER + "/stemmer";

    //admin pages
    public final static String baseAdminpages = "/adminpages",
            adminBaseStatistikk = baseAdminpages + "/statistikk",
            adminStatistikk = adminBaseStatistikk + "/statistikk.html",
            dashboardAdmin = adminBaseStatistikk + "/dashboard",
            adminBaseRegistrering = baseAdminpages + "/registrering",
            REGISTRER_BEDRIFT_HTML = adminBaseRegistrering + "/registrer_bedrift",
            REGISTRER_PROSJEKT_HTML = adminBaseRegistrering + "/registrer_prosjekt";

    //Misc
    public final static String LOGOUT = "/LOGOUT",
            LOGIN = "/login",
            LOGIN_HTML = "login",
            USER_LOGIN = "/user/login",
            USER_LOGIN_HTML = "userLogin",
            STEM = "/stem",
            MINE_STEMMER = "/mine_stemmer",
            ADMIN_INDEX = "/adminindex",
            INDEX_HTML = "index",
            REGISTRER_DEG = "/registrer_deg",
            ALLE_BEDRIFTER = "/bedrifter",
            ALLE_BEDRIFTER_HTML = baseAdminpages + "/bedrifter",
            baseStatistikk = "/statistikk",
            ERRORPAGE = "/ERRORPAGE";

}


