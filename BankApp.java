import java.util.Scanner;

class Rekening {
    private String nama;
    private String noRek;
    private String password;
    private double saldo;

    public void setNama(String nama) {
        if (nama.length() >= 4) {
            this.nama = nama;
        } else {
            System.out.println("Nama minimal 4 huruf!");
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNoRek(String noRek) {
        if (noRek.length() >= 8) {
            this.noRek = noRek;
        } else {
            System.out.println("Nomor rekening minimal 8 karakter!");
        }
    }

    public String getNoRek() {
        return noRek;
    }

    public void setPassword(String password) {
        if (password.length() >= 8 && password.contains("_") && password.matches(".*[A-Z].*")) {
            this.password = password;
        } else {
            System.out.println("Password harus minimal 8 karakter dan minimal 1 huruf besar!");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setSaldoAwal(double saldoAwal) {
        if (saldoAwal >= 0) {
            this.saldo = saldoAwal;
        } else {
            System.out.println("Saldo awal tidak boleh minus!");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void setor(double jumlah, String password) {
        if (!this.password.equals(password)) {
            System.out.println("Password salah, transaksi gagal!");
            return;
        }
        saldo += jumlah;
        System.out.println("Setor berhasil. Saldo sekarang: " + saldo);
    }

    public void tarik(double jumlah, String password) {
        if (!this.password.equals(password)) {
            System.out.println("Password salah, transaksi gagal!");
            return;
        }
        if (jumlah > saldo) {
            System.out.println("Saldo tidak cukup!");
            return;
        }
        saldo -= jumlah;
        System.out.println("Tarik berhasil. Saldo sekarang: " + saldo);
    }

    public void info() {
        System.out.println("================ Informasi Rekening ===============");
        System.out.println("Nama       : " + nama);
        System.out.println("No Rekening: " + noRek);
        System.out.println("Saldo      : " + saldo);
        System.out.println("===================================================");
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Rekening rek = new Rekening();

        System.out.println("==================== Data User ====================");
        System.out.print("Masukkan nama: ");
        rek.setNama(input.nextLine());

        System.out.print("Masukkan nomor rekening: ");
        rek.setNoRek(input.nextLine());

        System.out.print("Masukkan password: ");
        rek.setPassword(input.nextLine());

        System.out.print("Masukkan saldo awal: ");
        rek.setSaldoAwal(input.nextDouble()); 

        System.out.println("\nRegistrasi berhasil!\n");

        int pilihan = 0;
        do {
            System.out.println("\n=============== MENU REKENING ===============");
            System.out.println("1. Informasi Rekening");
            System.out.println("2. Cek Saldo");
            System.out.println("3. Setor");
            System.out.println("4. Tarik");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); 
            switch (pilihan) {
                case 1:
                    rek.info();
                    break;
                case 2:
                    System.out.println("Saldo Anda saat ini: " + rek.getSaldo());
                    break;
                case 3:
                    System.out.print("Masukkan password untuk setor: ");
                    String pwSetor = input.nextLine();
                    System.out.print("Jumlah setor: ");
                    double jumlahSetor = input.nextDouble();
                    input.nextLine();
                    rek.setor(jumlahSetor, pwSetor);
                    break;
                case 4:
                    System.out.print("Masukkan password untuk tarik: ");
                    String pwTarik = input.nextLine();
                    System.out.print("Jumlah tarik: ");
                    double jumlahTarik = input.nextDouble();
                    input.nextLine(); 
                    rek.tarik(jumlahTarik, pwTarik);
                    break;
                case 5:
                    System.out.println("Terima kasih! Transaksi selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);

        input.close();
    }
}
