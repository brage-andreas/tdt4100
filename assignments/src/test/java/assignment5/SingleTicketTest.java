package oving5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingleTicketTest {

    private Ticket ticket;

    @BeforeEach
    public void setup() {
        ticket = new SingleTicket();
    }

    @Test
    @DisplayName("Sjekk at billett returnerer true når den scannes for første gang")
    public void testReturnsTrueWhenScanned() {
        Assertions.assertTrue(this.ticket.scan(), "Billett skal returnere true når det scannes for første gang");
    }

    @Test
    @DisplayName("Sjekk at billett returnerer false når den scannes for andre gang")
    public void testReturnsFalseWhenScannedTwice() {
        this.ticket.scan();
        Assertions.assertFalse(this.ticket.scan(), "Billett skal returnere false når det scannes for andre gang");
    }

    @Test
    @DisplayName("Sjekk at billett returnerer true når den scannes for første gang etter reset")
    public void testReturnsFalseWhenScannedTwiceAfterReset() {
        this.ticket.scan();
        this.ticket = new SingleTicket();
        Assertions.assertTrue(this.ticket.scan(),
                "Billett skal returnere true når det scannes for første gang etter reset");
    }
}
