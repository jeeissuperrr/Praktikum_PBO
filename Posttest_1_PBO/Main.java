import java.util.ArrayList;
import java.util.Scanner;

class Lapangan {
    int id;
    String nama;
    double harga;

    public Lapangan(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public void tampilkanInfo() {
        System.out.println("ID: " + id + ", Nama: " + nama + ", Harga: Rp" + harga + "/jam");
    }
}

class Reservasi {
    int id;
    String namaPelanggan;
    String tanggal;
    int durasi;
    Lapangan lapangan;

    public Reservasi(int id, String namaPelanggan, String tanggal, int durasi, Lapangan lapangan) {
        this.id = id;
        this.namaPelanggan = namaPelanggan;
        this.tanggal = tanggal;
        this.durasi = durasi;
        this.lapangan = lapangan;
    }

    public void tampilkanInfo() {
        System.out.println("ID Reservasi: " + id + ", Pelanggan: " + namaPelanggan + 
                           ", Tanggal: " + tanggal + ", Durasi: " + durasi + " jam, Lapangan: " + lapangan.nama);
    }
}

public class Main {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Lapangan> daftarLapangan = new ArrayList<>();
    static ArrayList<Reservasi> daftarReservasi = new ArrayList<>();
    static int idReservasi = 1;

    public static void main(String[] args) {
        // Data lapangan yang sudah tersedia
        daftarLapangan.add(new Lapangan(1, "Lapangan A", 50000));
        daftarLapangan.add(new Lapangan(2, "Lapangan B", 60000));

        while (true) {
            System.out.println("\n=== Sistem Reservasi Lapangan Futsal ===");
            System.out.println("1. Lihat Daftar Lapangan");
            System.out.println("2. Buat Reservasi");
            System.out.println("3. Lihat Reservasi Saya");
            System.out.println("4. Edit Reservasi");
            System.out.println("5. Hapus Reservasi");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1: lihatLapangan(); break;
                case 2: tambahReservasi(); break;
                case 3: lihatReservasi(); break;
                case 4: editReservasi(); break;
                case 5: hapusReservasi(); break;
                case 6: System.out.println("Terima kasih!"); return;
                default: System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void lihatLapangan() {
        System.out.println("\nDaftar Lapangan:");
        for (Lapangan l : daftarLapangan) {
            l.tampilkanInfo();
        }
    }

    static void tambahReservasi() {
        System.out.print("Nama Pelanggan: ");
        String nama = input.nextLine();
        System.out.print("Tanggal (YYYY-MM-DD): ");
        String tanggal = input.nextLine();
        System.out.print("Durasi (jam): ");
        int durasi = input.nextInt();
        lihatLapangan();
        System.out.print("Pilih ID Lapangan: ");
        int idLap = input.nextInt();
        Lapangan lapangan = cariLapangan(idLap);
        if (lapangan != null) {
            daftarReservasi.add(new Reservasi(idReservasi++, nama, tanggal, durasi, lapangan));
            System.out.println("Reservasi berhasil ditambahkan!");
        } else {
            System.out.println("Lapangan tidak ditemukan.");
        }
    }

    static void lihatReservasi() {
        if (daftarReservasi.isEmpty()) {
            System.out.println("Belum ada reservasi.");
        } else {
            System.out.println("\nDaftar Reservasi:");
            for (Reservasi r : daftarReservasi) {
                r.tampilkanInfo();
            }
        }
    }

    static void editReservasi() {
        lihatReservasi();
        if (daftarReservasi.isEmpty()) return;
        
        System.out.print("Masukkan ID reservasi yang ingin diedit: ");
        int id = input.nextInt();
        input.nextLine();
        
        Reservasi reservasi = cariReservasi(id);
        if (reservasi != null) {
            System.out.print("Tanggal Baru (YYYY-MM-DD): ");
            reservasi.tanggal = input.nextLine();
            System.out.print("Durasi Baru (jam): ");
            reservasi.durasi = input.nextInt();
            System.out.println("Reservasi berhasil diperbarui!");
        } else {
            System.out.println("Reservasi tidak ditemukan.");
        }
    }

    static void hapusReservasi() {
        lihatReservasi();
        if (daftarReservasi.isEmpty()) return;

        System.out.print("Masukkan ID reservasi yang ingin dihapus: ");
        int id = input.nextInt();
        Reservasi reservasi = cariReservasi(id);
        if (reservasi != null) {
            daftarReservasi.remove(reservasi);
            System.out.println("Reservasi berhasil dihapus!");
        } else {
            System.out.println("Reservasi tidak ditemukan.");
        }
    }

    static Lapangan cariLapangan(int id) {
        for (Lapangan l : daftarLapangan) {
            if (l.id == id) return l;
        }
        return null;
    }

    static Reservasi cariReservasi(int id) {
        for (Reservasi r : daftarReservasi) {
            if (r.id == id) return r;
        }
        return null;
    }
}
