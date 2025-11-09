package mk.ukim.finki.wp.lab.web.controler;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String makeReservation(@RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam int numCopies,
                                  @RequestParam String bookTitle,
                                  HttpServletRequest request,
                                  Model model) {

        String clientIp = request.getRemoteAddr();

        bookReservationService.placeReservation(
                bookTitle,
                readerName,
                readerAddress,
                numCopies
        );

        model.addAttribute("clientIp", clientIp);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("readerName", readerName);
        model.addAttribute("numCopies", numCopies);

        return "reservationConfirmation";
    }
}
