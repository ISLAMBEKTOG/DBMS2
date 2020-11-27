package org.example.util;

import org.example.dao.category.CategoryDAO;
import org.example.dao.category.CategoryDAOImpl;
import org.example.dao.city.CityDAO;
import org.example.dao.city.CityDAOImpl;
import org.example.dao.region.RegionDAO;
import org.example.dao.role.RoleDAO;
import org.example.dao.specialist.SpecialistsDAO;
import org.example.dao.user.UserDAO;
import org.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@Component
public class DataLoader implements ApplicationRunner {
    private final SpecialistsDAO specialistsDAO;
    private final CategoryDAO categoryDAO;
    private final CategoryDAOImpl categoryDAOImpl;
    private final RegionDAO regionDAO;
    private final CityDAO cityDAO;
    private final CityDAOImpl cityDAOImpl;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;


    private static final String pathToRegionAndCitySite = "https://33tura.ru/goroda-kazahstana";

    private static final String path = "https://market.kz/almaty/uslugi/stroitelstvo-remont/santehnika/?page=";
    private static final String path1 = "https://market.kz/almaty/uslugi/stroitelstvo-remont/elektrika/?page=";

    private static final String path2 = "https://market.kz/almaty/uslugi/obrazovanie-kursy/doshkolnoe-obuchenie/?page=";
    private static final String path3 = "https://market.kz/almaty/uslugi/obrazovanie-kursy/kursy/?page=";

    private static final String path4 = "https://market.kz/almaty/uslugi/krasota-zdorove/massazh/?page=";
    private static final String path5 = "https://market.kz/almaty/uslugi/krasota-zdorove/medicinskie-uslugi/?page=";

    private static final String path6 = "https://market.kz/almaty/uslugi/informacionnye-tehnologii/kompyuternaya-pomoshch/?page=";
    private static final String path7 = "https://market.kz/almaty/uslugi/informacionnye-tehnologii/sozdanie-saytov/?page=";

    private static final String path8 = "https://market.kz/almaty/uslugi/avtouslugi/passazhirskie-perevozki/?page=";
    private static final String path9 = "https://market.kz/almaty/uslugi/avtouslugi/avtoservis/?page=";

    private static final String path10 = "https://market.kz/almaty/uslugi/uborkaa/uborka-kvartir-domov/?page=";
    private static final String path11 = "https://market.kz/almaty/uslugi/uborkaa/himchistka-kovrov-mebeli-shtor/?page=";

    private static final List<String> pathList = Arrays.asList(path, path1, path2, path3, path4,
            path5, path6, path7, path8, path9, path10, path11);
    private static int size = 1;
    private static int index = 0;

    @Autowired
    public DataLoader(SpecialistsDAO specialistsDAO, CategoryDAO categoryDAO,
                      CategoryDAOImpl categoryDAOImpl, RegionDAO regionDAO, CityDAO cityDAO, CityDAOImpl cityDAOImpl, UserDAO userDAO, RoleDAO roleDAO) {
        this.specialistsDAO = specialistsDAO;
        this.categoryDAO = categoryDAO;
        this.categoryDAOImpl = categoryDAOImpl;
        this.regionDAO = regionDAO;
        this.cityDAO = cityDAO;
        this.cityDAOImpl = cityDAOImpl;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws MalformedURLException {
        //Insert initial data to user and role tables
        userAndRoleTables();

        // Insert all data from site to region and city tables
        generateRegionAndCity(new URL(pathToRegionAndCitySite));

        // Insert all data from site to specialists and role tables
        for (int i = 0; i < 12; i++) {
            index = i;
            categoryDAO.save(new Category(pathList.get(i).trim().split("/")[6]));
            generatePage(new URL(pathList.get(i) + size));
            size = 1;
        }
    }

    public void generatePage(URL url) {
        String image = "";
        String title = "";
        String content = "";
        int price = 0;
        String cityName = "";
        String date = "";
        String categoryName = url.getPath().trim().split("/")[4];

        int count = 0;

        try {

            LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
            String string = reader.readLine();


            System.out.println(url.toString());
            while (string != null) {
                if (count == 21) {
                    count = 0;
                    generatePage(new URL(pathList.get(index) + ++size));
                }
                if(string.contains("<div id=\"content-left\" class=\"search-page grid_16 alpha omega\">")) {
                    string = reader.readLine();
                    if(string.contains("Страница")) {
                        string = string.trim().split("<h1>")[1].split("</h1>")[0].split("-")[1].trim().split(" ")[1];

                        int end = Integer.parseInt(string);

                        if(end < size) {
                            reader.close();
                        }
                    }
                }
                if (string.contains("a-card__img")) {
                    string = reader.readLine();
                    image = getImageFromSite(string.trim());
                } else if (string.contains("a-card__link")) {
                    string = reader.readLine();
                    title = string.trim();
                } else if (string.contains("<span class=\"description\">")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (!string.contains("</span>")) {
                        string = reader.readLine().trim();
                        stringBuilder.append(string);
                        stringBuilder.append(" ");
                    }
                    String s = stringBuilder.toString();
                    content = s.split("<span class=\"description-name\">")[1].split("</span>")[0].trim();
                } else if (string.contains("a-card__price")) {
                    string = reader.readLine().trim();
                    if (string.contains("<span class=\"prices tenge rate-1\">")) {
                        string = string.split("<span class=\"prices tenge rate-1\">")[1].split(" ₸</span>")[0].trim();
                        StringBuilder sb = new StringBuilder();
                        String[] strings = string.split(" ");
                        for (String s : strings) {
                            sb.append(s);
                        }
                        price = Integer.parseInt(sb.toString());
                    }
                } else if (string.contains("card-stats__item")) {
                    string = reader.readLine();
                    if (string.contains("add-date")) {
                        String s = string.trim().split("<span class=\"add-date\">")[1].split("</span>")[0].trim();
                        date = convertDate(s);
                    } else if (!string.contains("card-stats__views views")) {
                        string = string.trim();
                        if (!string.equals("")) {
                            cityName = string;
                        }
                    }
                } else if (string.contains("<span class=\"services-icons js__services-icons")) {
                    Date dateTime = new SimpleDateFormat("dd/MM/yyyy").parse(date);

                    Category category = categoryDAOImpl.findByName(categoryName).isPresent()
                            ? categoryDAOImpl.findByName(categoryName).get() : null;

                    City city = cityDAOImpl.findByName(cityName).isPresent() ? cityDAOImpl.findByName(cityName).get() : null;

                    if (category != null && city != null) {
                        count++;
                        specialistsDAO.save(new Specialists(title, category, content, price, city, dateTime, image));
                    }
                }
                string = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getImageFromSite(String s) {
        String string = "";
        if (s.contains("photos-mt.")) {
            string = s.split("src=")[1].split("\"")[1].split("//")[1];
        }
        return string;
    }

    public static String convertDate(String s) {
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strings[0]);
        stringBuilder.append("/");

        switch (strings[1]) {
            case "января":
                stringBuilder.append("01");
                break;
            case "февраля":
                stringBuilder.append("02");
                break;
            case "марта":
                stringBuilder.append("03");
                break;
            case "апреля":
                stringBuilder.append("04");
                break;
            case "мая":
                stringBuilder.append("05");
                break;
            case "июня":
                stringBuilder.append("06");
                break;
            case "июля":
                stringBuilder.append("07");
                break;
            case "августа":
                stringBuilder.append("08");
                break;
            case "сентября":
                stringBuilder.append("09");
                break;
            case "октября":
                stringBuilder.append("10");
                break;
            case "ноября":
                stringBuilder.append("11");
                break;
            case "декабря":
                stringBuilder.append("12");
                break;
        }
        stringBuilder.append("/");
        stringBuilder.append(strings[2]);

        return stringBuilder.toString();
    }

    public void generateRegionAndCity(URL url) {
        String regionName = "";
        String cityName = "";

        try {
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
            String string = reader.readLine();
            while (string != null) {
                if (string.contains("<td>")) {
                    cityName = string.trim().split("<td>")[1].split("</td>")[0].trim();
                    string = reader.readLine();
                    string = reader.readLine();
                    regionName = string.trim().split("<td>")[1].split("</td>")[0].trim();

                    Region region = regionDAO.findByName(regionName).isPresent() ? regionDAO.findByName(regionName).get() : null;

                    if (region == null) {
                        regionDAO.save(new Region(regionName));
                        Region curRegion = regionDAO.findByName(regionName).isPresent() ? regionDAO.findByName(regionName).get() : null;
                        if (curRegion != null) {
                            cityDAO.save(new City(cityName, curRegion));
                        }
                    } else {
                        cityDAO.save(new City(cityName, region));
                    }
                }
                string = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userAndRoleTables() {
        roleDAO.save(new Role("ADMIN"));
        roleDAO.save(new Role("USER"));

        Role role = roleDAO.findRoleByName("USER");

        userDAO.save(new User("user", "12345678", "user1", "user2", 19, "user@gmail.com", role));
    }

}
