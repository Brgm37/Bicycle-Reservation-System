/*
MIT License

Copyright (c) 2024, Nuno Datia, Matilde Pato, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.ui;

import isel.sisinf.jpa.*;
import isel.sisinf.model.EntityClass.Bicicleta;
import isel.sisinf.model.EntityClass.Loja;
import isel.sisinf.model.EntityClass.Pessoa;
import isel.sisinf.model.EntityClass.Reserva;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.HashMap;

interface DbWorker
{
    void doWork();
}
class UI
{
    private enum Option
    {
        // DO NOT CHANGE ANYTHING!
        Unknown,
        Exit,
        createCostumer,
        listExistingBikes,
        checkBikeAvailability,
        obtainBookings,
        makeBooking,
        cancelBooking,
        about
    }
    private static UI __instance = null;
  
    private HashMap<Option,DbWorker> __dbMethods;

    private UI()  {
        // DO NOT CHANGE ANYTHING!
        __dbMethods = new HashMap<Option,DbWorker>();
        __dbMethods.put(Option.createCostumer, () -> UI.this.createCostumer());
        __dbMethods.put(Option.listExistingBikes, () -> UI.this.listExistingBikes());
        __dbMethods.put(Option.checkBikeAvailability, () -> UI.this.checkBikeAvailability());
        __dbMethods.put(Option.obtainBookings, new DbWorker() {public void doWork() {UI.this.obtainBookings();}});
        __dbMethods.put(Option.makeBooking, new DbWorker() {public void doWork() {UI.this.makeBooking();}});
        __dbMethods.put(Option.cancelBooking, new DbWorker() {public void doWork() {UI.this.cancelBooking();}});
        __dbMethods.put(Option.about, new DbWorker() {public void doWork() {UI.this.about();}});
    }

    public static UI getInstance() {
        // DO NOT CHANGE ANYTHING!
        if(__instance == null)
        {
            __instance = new UI();
        }
        return __instance;
    }

    private Option DisplayMenu() {
        Option option = Option.Unknown;
        Scanner s = new Scanner(System.in); //Scanner closes System.in if you call close(). Don't do it
        try
        {
            // DO NOT CHANGE ANYTHING!
            System.out.println("Bicycle reservation");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. Create Costumer");
            System.out.println("3. List Existing Bikes");
            System.out.println("4. Check Bike Availability");
            System.out.println("5. Current Bookings");
            System.out.println("6. Make a booking");
            System.out.println("7. Cancel Booking");
            System.out.println("8. About");
            System.out.print(">");
            int result = s.nextInt();
            option = Option.values()[result];
        }
        catch(RuntimeException ex) {
            //nothing to do.
        }
        return option;
    }
    private static void clearConsole() throws Exception {
        // DO NOT CHANGE ANYTHING!
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");
    }

    public void Run() throws Exception {
        // DO NOT CHANGE ANYTHING!
        Option userInput;
        do {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try {
                __dbMethods.get(userInput).doWork();
                System.in.read();
            }
            catch(NullPointerException ex) {
                //Nothing to do. The option was not a valid one. Read another.
            }

        }while(userInput != Option.Exit);
    }

    /**
    To implement from this point forward. Do not need to change the code above.
    -------------------------------------------------------------------------------     
        IMPORTANT:
    --- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
    --- Other Methods and properties can be added to support implementation -------
    -------------------------------------------------------------------------------
    */

    private static final int TAB_SIZE = 24;

    private void createCostumer() {
        try (IContext ctx = new JPAContext()) {
            System.out.println("createCostumer()");
            Scanner s = new Scanner(System.in);
            System.out.print("Name: ");
            String name = s.nextLine();
            System.out.print("Email: ");
            String email = s.nextLine();
            System.out.print("Address: ");
            String address = s.nextLine();
            System.out.print("Phone: ");
            String phone = s.nextLine();
            System.out.print("Identification Number: ");
            String noIdent = s.nextLine();
            System.out.print("Nationality: ");
            String nationality = s.nextLine();

            Pessoa p = new Pessoa(
                name,
                address,
                email,
                phone,
                noIdent,
                nationality,
                    'C'
            );
            ctx.beginTransaction();
            IPessoaRepository repository = ctx.getPessoas();
            repository.create(p);
            ctx.flush();
            System.out.println("Costumer created successfully ->" + repository.update(p));
            ctx.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
  
    private void listExistingBikes() {
        System.out.println("listExistingBikes()");
        try (IContext ctx = new JPAContext()) {
            IBicicletaRepository repository = ctx.getBicicletas();
            repository.find("SELECT b FROM Bicicleta b ").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    private void checkBikeAvailability() {
        System.out.println("checkBikeAvailability()");
        try(IContext ctx = new JPAContext()) {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the date: ");
            String date = s.nextLine();
            System.out.println("Enter the bike id: ");
            int id = s.nextInt();
            IBicicletaRepository repository = ctx.getBicicletas();
            System.out.println(repository.availability(id, date) ? "Bike is available" : "Bike is not available");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void obtainBookings() {
        System.out.println("obtainBookings()");
        try (IContext ctx = new JPAContext()) {
            IReservaRepository repository = ctx.getReservas();
            System.out.println("\nReservas: ");
            repository.find("SELECT r FROM Reserva r").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void makeBooking() {
        System.out.println("makeBooking()");
        try(IContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            ILojaRepository lojas = ctx.getLojas();
            IReservaRepository reservas = ctx.getReservas();
            IBicicletaRepository bicicletas = ctx.getBicicletas();
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the start date: ");
            String start = s.nextLine();
            System.out.println("Enter the end date: ");
            String end = s.nextLine();
            System.out.println("Enter price: ");
            double price = s.nextDouble();
            System.out.println("Enter the bike id: ");
            int id = s.nextInt();
            Bicicleta bicicleta = bicicletas.findByKey(id);
            System.out.println("Enter the store id: ");
            int lojaid = s.nextInt();
            Loja loja = lojas.findByKey(lojaid);
            Reserva r = new Reserva(loja, start, end, price, bicicleta);
            reservas.create(r);
            ctx.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Unable to make booking.");
        }
        
    }

    private void cancelBooking() {
        System.out.println("cancelBooking");
        try (IContext ctx = new JPAContext()) {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter the number of the booking you want to cancel: ");
            int noreserva = s.nextInt();

            ctx.beginTransaction();
            IReservaRepository reservas = ctx.getReservas();
            Reserva r = reservas.findByKey(noreserva);
            if (!r.getDtfim().isAfter(LocalDateTime.now())) {
                if (LocalDateTime.now().isAfter(r.getDtinicio())) {
                    Bicicleta bicicleta = r.getBicicleta();
                    bicicleta.setEstado("livre");
                    IBicicletaRepository bicicletas = ctx.getBicicletas();
                    bicicletas.update(bicicleta);
                }
                reservas.delete(r);
            }

            ctx.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void about() {
        System.out.println(
                """
                G34T43D
                Brian Melhorado - 50471
                Guilherme Belo - 50978
                Arthur Oliveira - 50543
                """
        );
        System.out.println("DAL version:"+ isel.sisinf.jpa.Dal.version());
        System.out.println("Core version:"+ isel.sisinf.model.Core.version());
        
    }
}

public class App {
    public static void main(String[] args) throws Exception{
        UI.getInstance().Run();
    }
}