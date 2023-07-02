package com.chatting.chatting.shop.service;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.global.util.FileUtil;
import com.chatting.chatting.shop.model.entity.Shop;
import com.chatting.chatting.shop.model.request.ShopCreateRequest;
import com.chatting.chatting.shop.model.response.ShopResponse;
import com.chatting.chatting.shop.model.type.CateType;
import com.chatting.chatting.shop.model.type.ProgressiveType;
import com.chatting.chatting.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final FileUtil fileUtil;
    private String DEFAULT_IMG = "defulat\\기본이미지.png";

    //상품 생성
    @Transactional
    public ShopResponse createShop(ShopCreateRequest req, User user, List<MultipartFile> images) {

        Shop shop = shopRepository.save(Shop.builder()
                .shopName(req.shopName())
                .shopContent(req.shopContent())
                .auction(req.auction())
                .cate(getCateType(req.cate()))
                .user(user)
                .img(getImages(images, user.getUserId()))
                .money(req.money())
                .progressive(ProgressiveType.WAIT)
                .build());
        return ShopResponse.getShopCreateRequest(shop);
    }

    //상품 수정
    @Transactional
    public ShopResponse updateShop(Long shopPid, ShopCreateRequest req, User user, List<MultipartFile> images) {
        Shop shop = shopRepository.findById(shopPid)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NO_SHOP);
                });
        if (shop.getUser().equals(user)) {
            shop.setShopName(req.shopName());
            shop.setShopContent(req.shopContent());
            shop.setImg(getImages(images, user.getUserId()));
            shop.setMoney(req.money());
            shop.setAuction(req.auction());
            shop.setCate(getCateType(req.cate()));
            return ShopResponse.getShopCreateRequest(shop);
        }
        throw new CustomException(ErrorCode.WRONG_SHOP_USER);

    }

    @Transactional
    public String deleteShop(Long shopPid, User user) {
        Shop shop = shopRepository.findById(shopPid)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NO_SHOP);
                });
        if (shop.getUser().equals(user)) {
            shopRepository.deleteById(shopPid);
            return "성공적으로 상품이 삭제되었습니다.";
        }
        throw new CustomException(ErrorCode.WRONG_SHOP_USER);

    }

    //상품 판매 완료
    @Transactional
    public String sellShop(Long shopPid, User user) {
        Shop shop = shopRepository.findById(shopPid)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NO_SHOP);
                });
        if (shop.getUser().equals(user)) {
            shop.setProgressive(ProgressiveType.SELL);
            return "상품 판매가 완료되었습니다.";
        }
        throw new CustomException(ErrorCode.WRONG_SHOP_USER);

    }

    private List<String> getImages(List<MultipartFile> files, String userId) {
        List<String> imageList = new ArrayList<>();
        if (Objects.isNull(files)) {
            imageList.add(DEFAULT_IMG);
        } else {
            return fileUtil.saveFileList(userId, files);
        }
        return imageList;
    }

    private CateType getCateType(String cate) {
        //TODO: 나중에 추가 할 예정.
        return switch (cate) {
            case "식품" -> CateType.EAT;
            case "전자제품" -> CateType.ELECTRONIC_PRODUCTS;
            default -> CateType.NOTHING;
        };

    }

}
