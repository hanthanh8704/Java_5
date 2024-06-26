package com.example.asm.repository;

import com.example.asm.model.HoaDon;
import com.example.asm.model.SanPham;
import com.example.asm.model.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    @Query("SELECT sp FROM SanPhamChiTiet sp WHERE sp.sanPham.ten LIKE %?1%")
    List<SanPhamChiTiet> searchSanPhamChiTietByTen(String ten);

    @Query("SELECT sp.soLuong FROM SanPhamChiTiet sp WHERE sp.id = :id")
    Integer getSoLuongTonByIDCTSP(@Param("id") Integer id);

    @Query("SELECT SUM(hd.soLuong) FROM HoaDonChiTiet hd WHERE hd.sanPhamChiTiet.id = :idCTSP")
    Integer getSoLuongTrongGioHangByIdCTSP(@Param("idCTSP") Integer idCTSP);
    @Query("SELECT h FROM SanPhamChiTiet h WHERE h.id = :id")
    SanPhamChiTiet findSPCTById(Integer id);


    @Query("SELECT c FROM SanPhamChiTiet c WHERE c.sanPham.ten LIKE %?1%")
    List<SanPhamChiTiet> searchProductDetailbyKeyWord(String keyword);

    @Query("SELECT pdt FROM SanPhamChiTiet  pdt where pdt.category.id = ?1")
    List<SanPhamChiTiet> getProductdetailByCate(Integer id);

    @Query("SELECT pdt FROM SanPhamChiTiet  pdt where pdt.sanPham.id = ?1")
    List<SanPhamChiTiet> getProductdetailBySanPham(Integer id);
    @Query("SELECT pdt FROM SanPhamChiTiet pdt WHERE pdt.mauSac.id = ?1")
    List<SanPhamChiTiet> findProductdetailByColor(Integer id);

    @Query("SELECT pdt FROM SanPhamChiTiet pdt WHERE pdt.mauSac.id = ?1")
    List<SanPhamChiTiet> findProductdetailBySize(Integer id);

    List<SanPhamChiTiet> findByDonGiaBetween(BigDecimal mindonGia, BigDecimal maxdonGia);

}
