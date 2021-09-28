package uz.rootec.appcovidserver.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceTransformer;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;
import uz.rootec.appcovidserver.entity.Patient;
import uz.rootec.appcovidserver.repository.PatientRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class MainPageTransformer implements ResourceTransformer {

    @Autowired
    PatientRepository patientRepository;

//    //
//    public MainPageTransformer() {
//        this.courseRepository = (CourseRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("courseRepository");
//        this.roadmapRepository = (RoadmapRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("roadmapRepository");
//        this.bootcampRepository = (BootcampRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("bootcampRepository");
//
//    }


    @Override
    public Resource transform(HttpServletRequest request, Resource resource, ResourceTransformerChain transformerChain) throws IOException {
        String url = request.getRequestURI();
        String html = IOUtils.toString(resource.getInputStream(), "UTF-8");
        if (url.contains("/sertificate")) {
            try {
                String urls[] = url.split("/");
                Patient patient = patientRepository.getOne(UUID.fromString(urls[2]));
                String stringDate = patient.getAnaliseDate();
                Date date1=new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(stringDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date1);
                cal.add(Calendar.HOUR_OF_DAY, 4);
                Date time = cal.getTime();
                DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                String format = targetFormat.format(date1);
                String format1 = targetFormat.format(time);
                html = html.replaceAll("<div id=\"root\"></div>", "<div style=\"width:1024px !important;margin:0 auto !important;font-family: 'Roboto', sans-serif;     background-size: 100% 100%;background-repeat: no-repeat;     padding: 20px;    box-sizing: border-box;\"\n" +
                            "      data-new-gr-c-s-check-loaded=\"14.1027.0\" data-gr-ext-installed=\"\" cz-shortcut-listen=\"true\">" +
                            "<table style=\"width:100% !important; margin-bottom:30px !important;margin-top:5px !important;font-family: 'Roboto', sans-serif;\">\n" +
                            "    <tbody>\n" +
                            "    <tr>\n" +
                            "        <td style=\"width:375px !important;font-size:24px !important;text-align:center !important;padding-left:14px !important;padding-right:14px !important;font-family: 'Roboto', sans-serif;vertical-align: top;padding-top: 15px;\">\n" +
                            "            MINISTRY OF HEALTH OF <br>THE REPUBLIC OF <br>UZBEKISTAN <br>CONFIRMATION OF <br>COVID-19 TEST RESULT\n" +
                            "        </td>\n" +
                            "        <td style=\"width:220px !important\"><img src=\"/gerb.png\" width=\"200\" height=\"207\"></td>\n" +
                            "        <td style=\"width:375px !important;font-size:24px !important;text-align:center !important;padding-left:20px !important;padding-right:20px !important;font-family: 'Roboto', sans-serif;vertical-align: top;padding-top: 15px;\">\n" +
                            "            МИНИСТЕРСТВО <br>ЗДРАВООХРАНЕНИЯ <br>РЕСПУБЛИКИ УЗБЕКИСТАН <br>ПОДТВЕРЖДЕНИЕ <br>РЕЗУЛЬТАТА ТЕСТА <br>COVID-19\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    </tbody>\n" +
                            "</table>\n" +
                            "<div style=\"width:100% !important;font-size:24px !important;margin-left:13px !important; margin-right: 13px; box-sizing: border-box;font-family: 'Roboto', sans-serif;\">\n" +
                            "    <p>ID / Номер: <span id=\"serial\">"+ patient.getSerial() +"</span></p>\n" +
                            "    <p>Laboratory (name) / Лаборатория (название): <span id=\"laboratory\">"+ patient.getLaboratory().getNameEn() + " / " + patient.getLaboratory().getNameRu() +"</span></p>\n" +
                            "    <p>Place of sampling / Место забора анализа: <span id=\"place\">"+ patient.getPlace().getNameEn() + " / " + patient.getPlace().getNameRu() +"</span></p>\n" +
                            "    <p style=\"\">Research method / Метод исследования: <span id=\"method\">"+ patient.getMethodEn() + " / " + patient.getMethodRu() +"</span></p>\n" +
                            "    <div style=\"display:block;width:97%;height:4px;border-bottom: 4px solid #9c9fa4;margin-bottom: 15px;margin-top: 5px; \"></div>\n" +
                            "    <p>Серия и номер паспорта: <span id=\"passport\">" + patient.getPassportNumber() +"</span></p>\n" +
                            "    <p>Full name / Полное имя: <span id=\"fullname\">" + patient.getFullName() +"</span></p>\n" +
                            "    <p>Birth date / Дата рождения: <span id=\"birthday\">" + patient.getBirthDate() + "</span></p>\n" +
                            "    <p>Sex / Пол: <span id=\"gender\">"+ patient.getGender() + "</span></p>\n" +
                            "    <p>Analysis date / Дата сдачи анализа: <span id=\"analise\">" + format + "</span></p>\n" +
                            "    <p>Test result and date / Результат и дата теста: <span id=\"result\">" + patient.getStatus() + " ("+format1+") " +"</span>.</p>\n" +
                            "    <div style=\"text-align:center\"><img\n" +
                            "            src=\"https://api.qrserver.com/v1/create-qr-code/?size=320x320&data=https://app-server-app.herokuapp.com/viewResult/"+ url.split("/")[2] +"\"\n" +
                            "            id=\"qr\"\n" +
                            "            style=\"width:320px!important\"></div>\n" +
                            "</div>\n" +
                            "</div>");
            } catch (Exception e) {
                e.printStackTrace();
                return new TransformedResource(resource, html.getBytes());
            }
        }


        return new TransformedResource(resource, html.getBytes());
    }


}
