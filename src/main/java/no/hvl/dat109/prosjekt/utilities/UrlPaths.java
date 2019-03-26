package no.hvl.dat109.prosjekt.utilities;

public class UrlPaths {
    //Userpages
    public final static String BASE_USERPAGES_HTML = "/userpages",
            BEDRIFT_HTML = BASE_USERPAGES_HTML + "/bedrift",
            KONTAKT_HTML = BASE_USERPAGES_HTML + "/kontakt",
            BASE_KONTAKT = "/kontakt",
            STAND_HTML = BASE_USERPAGES_HTML + "/stand",
            STAND_DELTAGELSE_HTML = BASE_USERPAGES_HTML + "/stand_deltagelse",
            MINE_STEMMER_HTML = BASE_USERPAGES_HTML + "/mine_stemmer",
            TAKK_FOR_STEMME_HTML = BASE_USERPAGES_HTML + "/takk_for_stemme";

    //Bedrift
    public final static String BEDRIFT_BASE = "/bedrift",
            BEDRIFT_WITH_ID = BEDRIFT_BASE + "/{id}",
            ADD_BEDRIFT = BEDRIFT_BASE + "/add";

    //Arrangement
    public final static String ARRANGEMENT_BASE = "/arrangement",
            ARRANGEMENT_WITH_ID = ARRANGEMENT_BASE + "/{id}",
            ARRANGEMNT_DELTAGELSE_HTML = "deltagelser";

    //Prosjekt
    public final static String
            BASE_PROSJEKT = "/prosjekt",
            PROSJEKT_WITH_ID = BASE_PROSJEKT + "/{id}",
            PROSJEKT_WITH_ID_AND_ARRANGEMENT = PROSJEKT_WITH_ID + "/arrangement/{arrangementid}",
            PROSJEKT_ENDRE_NAVN = PROSJEKT_WITH_ID + "/endrenavn",
            PROSJEKT_ENDRE_BESKRIVELSE = PROSJEKT_WITH_ID + "/endrebeskrivelse",
            ADD_PROSJEKT = BASE_PROSJEKT + "/add",
            PROSJEKT_STATISTIKK = PROSJEKT_WITH_ID + "/statistikk",
            SHOW_QR = PROSJEKT_WITH_ID + "/qr",
            CREATE_QR_IMAGE = SHOW_QR + "/create",
            REMOVE_PROSJEKT = PROSJEKT_WITH_ID + "/remove",
            UPLOAD_PROSJEKT_IMAGES = PROSJEKT_WITH_ID + "/upload",
            UPLOAD_HTML = "upload.html",
            ALLE_PROSJEKTER = "/prosjekter",


    REMOVE_ALL_PROSJEKT_FILES = ALLE_PROSJEKTER + "/apocalypse";

    //Dashboard
    public final static String DASHBOARD = "/dashboard",
            PROSJEKT_DASHBOARD = DASHBOARD + "/{id}",
            PROSJEKT_ARRANGEMENT_DASHBOARD = PROSJEKT_DASHBOARD + "/arrangement/{arrangementid}";

    public final static String KATEGORI_BASE = "/kategori",
            KATEGORI_WITH_ID = KATEGORI_BASE + "/{id}",
            REMOVE_KATEGORI = KATEGORI_WITH_ID + "/remove",
            ADD_KATEGORI = KATEGORI_BASE + "/add";

    //Stand pages
    public final static String baseStandPages = "/standpages",
            STAND_STATISTIKK_HTML = baseStandPages + "/stand_statistikk",
            STAND_DASHBOARD_HTML = baseStandPages + "/dashboard",
            STAND_ARRANGEMENT_DASHBOARD_HTML = baseStandPages + "/dashboard_arrangement",
            STAND_QR_HTML = baseStandPages + "/qrkode";
    //Api
    public final static String API_BASE = "/api",
            API_PROSJEKT = API_BASE + "/prosjekt/{id}/arrangement/{arrangementid}",
            STEM_HTML = API_PROSJEKT + "/stemmer",
            API_PROSJEKTER = API_BASE + "/prosjekter",
            API_PROSJEKTER_STEMMER = API_PROSJEKTER + "/stemmer";

    //admin pages
    public final static String ADMINPAGES_BASE = "/adminpages",
            ADMIN_BASE_STATISTIKK = ADMINPAGES_BASE + "/statistikk",
            ADMIN_STATISTIKK_HTML = ADMIN_BASE_STATISTIKK + "/statistikk.html",
            DASHBOARD_ADMIN_HTML = ADMIN_BASE_STATISTIKK + "/dashboard.html",
            ADMIN_REGISTRERING_BASE = ADMINPAGES_BASE + "/registrering",
            REGISTRER_BEDRIFT_HTML = ADMIN_REGISTRERING_BASE + "/registrer_bedrift",
            REGISTRER_PROSJEKT_HTML = ADMIN_REGISTRERING_BASE + "/registrer_prosjekt",
            ALLE_PROSJEKTER_HTML = ADMINPAGES_BASE + "/prosjekter.html";

    //Misc
    public final static String LOGOUT = "/logout",
            LOGIN = "/login",
            LOGIN_HTML = "login",
            USER_LOGIN = "/user/login",
            USER_LOGIN_HTML = "userLogin",
            LOGGED_IN_HTML = "loggedinIndex",
            INDEX = "/",
            STEM = "/stem",
            MINE_STEMMER = "/mine_stemmer",
            ADMIN_INDEX = "/adminindex",
            INDEX_HTML = "index",
            REGISTRER_DEG = "/registrer_deg",
            REGISTRER_DEG_HTML = "registrer_deg",
            ALLE_BEDRIFTER = "/bedrifter",
            ALLE_BEDRIFTER_HTML = ADMINPAGES_BASE + "/bedrifter",
            BASE_STATISTIKK = "/statistikk",
            ERRORPAGE = "error";

}


