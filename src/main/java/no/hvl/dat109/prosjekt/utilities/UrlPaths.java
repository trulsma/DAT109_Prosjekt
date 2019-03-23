package no.hvl.dat109.prosjekt.utilities;

public class UrlPaths {
    //Userpages
    public final static String BASE_USERPAGES_HTML = "/userpages",
            BEDRIFT_HTML = BASE_USERPAGES_HTML + "/bedrift",
            KONTAKT_HTML = BASE_USERPAGES_HTML + "/kontakt",
            STAND_HTML = BASE_USERPAGES_HTML + "/stand",
            MINE_STEMMER_HTML = BASE_USERPAGES_HTML + "/mine_stemmer",
            TAKK_FOR_STEMME_HTML = BASE_USERPAGES_HTML + "/takk_for_stemme";

    //Bedrift
    public final static String baseBedrift = "/bedrift",
            BEDRIFT_WITH_ID = baseBedrift + "/{id}",
            ADD_BEDRIFT = baseBedrift + "/add";

    //Arrangement
    public final static String arrangementBase = "/arrangement",
            ARRANGEMENT_WITH_ID = arrangementBase + "/{id}";

    //Misc
    public final static String LOGOUT = "/LOGOUT",
            baselogin = "/login",
            LOGIN_USER = baselogin + "/{id}",
            STEM = "/stem",
            MINE_STEMMER = "/mine_stemmer",
            ADMIN_INDEX = "/adminindex",
            REGISTRER_DEG = "/registrer_deg",
            ALLE_BEDRIFTER = "/bedrifter",
            baseStatistikk = "/statistikk",
            ERRORPAGE = "/ERRORPAGE";

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

}


