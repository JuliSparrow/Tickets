package ru.netology.javaqa.javaqamvn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AviaSoulsTest {

    @Test
    void shouldCompareTickets() {
        Ticket ticket1 = new Ticket("Калининград", "Сочи", 14300, 1500, 1900);
        Ticket ticket2 = new Ticket("Калининград", "Москва", 8990, 1530, 1800);
        Ticket ticket3 = new Ticket("Калининград", "Санкт-Петербург", 8990, 1915, 2150);

        int actual1 = ticket1.compareTo(ticket2);
        Assertions.assertTrue(actual1 > 0);

        int actual2 = ticket2.compareTo(ticket1);
        Assertions.assertTrue(actual2 < 0);

        int actual3 = ticket2.compareTo(ticket3);
        Assertions.assertTrue(actual3 == 0);
    }

    @Test
    public void shouldSortTicketsAsc() {
        Ticket ticket1 = new Ticket("Калининград", "Сочи", 14300, 1500, 1900);
        Ticket ticket2 = new Ticket("Екатеринбург", "Москва", 8990, 1530, 1800);
        Ticket ticket3 = new Ticket("Калининград", "Москва", 7990, 1915, 2150);
        Ticket ticket4 = new Ticket("Калининград", "Сочи", 15600, 1500, 1900);
        Ticket ticket5 = new Ticket("Сочи", "Екатеринбург", 7990, 1915, 2150);
        Ticket ticket6 = new Ticket("Санкт-Петербург", "Сочи", 6650, 1915, 2150);
        Ticket ticket7 = new Ticket("Новороссийск", "Сочи", 5680, 1915, 2150);
        Ticket ticket8 = new Ticket("Калининград", "Сочи", 14100, 1500, 1900);

        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);
        aviaSouls.add(ticket7);
        aviaSouls.add(ticket8);

        Ticket[] expected = new Ticket[] {ticket8, ticket1, ticket4};
        Ticket[] actual = aviaSouls.search("Калининград", "Сочи");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByTime() {
        Ticket ticket1 = new Ticket("Калининград", "Сочи", 14300, 15, 19);
        Ticket ticket2 = new Ticket("Калининград", "Москва", 8990, 16, 20);
        Ticket ticket3 = new Ticket("Калининград", "Санкт-Петербург", 8990, 13, 15);
        TicketTimeComparator comparator = new TicketTimeComparator();

        //Время полёта одинаковое
        int actual1 = comparator.compare(ticket1, ticket2);
        Assertions.assertTrue(actual1 == 0);

        //Время по ticket2 - 4 часа, по ticket3 - 2 часа
        int actual2 = comparator.compare(ticket2, ticket3);
        Assertions.assertTrue(actual2 > 0);

        int actual3 = comparator.compare(ticket3, ticket1);
        Assertions.assertTrue(actual3 < 0);
    }

    @Test
    public void shouldSortTicketsUsingComparator() {
        Ticket ticket1 = new Ticket("Калининград", "Сочи", 14300, 15, 19);  //4
        Ticket ticket2 = new Ticket("Калининград", "Сочи", 8990, 15, 20);    //5
        Ticket ticket3 = new Ticket("Калининград", "Сочи", 7990, 15, 21); //6
        Ticket ticket4 = new Ticket("Калининград", "Сочи", 15600, 11, 14);  //3
        Ticket ticket5 = new Ticket("Калининград", "Сочи", 7990, 11, 13);  //2
        Ticket ticket6 = new Ticket("Санкт-Петербург", "Сочи", 6650, 11, 16);   //5

        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = new Ticket[] { ticket5, ticket4, ticket1, ticket2, ticket3 };
        Ticket[] actual = aviaSouls.searchAndSortBy("Калининград", "Сочи", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}