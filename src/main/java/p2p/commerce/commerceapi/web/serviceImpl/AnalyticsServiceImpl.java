package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.web.dto.AnalyticsAdminResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsMostProductResponse;
import p2p.commerce.commerceapi.web.dto.AnalyticsSellerResponse;
import p2p.commerce.commerceapi.web.model.ProductTransactions;
import p2p.commerce.commerceapi.web.model.Products;
import p2p.commerce.commerceapi.web.model.Sellers;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.AnalyticsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private ConsultationRepository consultationRepository;
    private ProductTransactionRepository productTransactionRepository;
    private AuthenticationFacade authenticationFacade;
    private SellesRepository sellesRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    private static int allAmount = 0;
    private static int lastMonthAmount = 0;

    @Transactional(readOnly = true)
    @Override
    public AnalyticsAdminResponse findAnalyticsAdmin() {

        Date date = new Date();
        date.setMonth(new Date().getMonth()-1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String format = formatter.format(date);
        long listConsultation = consultationRepository.count();
        long newConsultation = consultationRepository.countSubConsultant(format);
        return AnalyticsAdminResponse.builder()
                .consultation(listConsultation)
                .subConsultationPercent(((((listConsultation+ 0.0))-(newConsultation+ 0.0))/(listConsultation))*100.0)
                .transaction(productTransactionRepository.count())
                .subTransaction(productTransactionRepository.countProductTransactionThisDate(format))
                .totalStudent(clientRepository.count())
                .subTotalMentor(userRepository.countUserCurrentMonth(format, 2))
                .totalMentor(sellesRepository.count())
                .subTotalStudent(userRepository.countUserCurrentMonth(format, 3))
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnalyticsMostProductResponse> analyticsMostProduct() {
        Users user = authenticationFacade.getAuthentication();
        Pageable topTen = PageRequest.of(0, 10);
        if (user.getUserType().getUserTypeName().equals("Admin")) {
            return productRepository.findAllByDeleteDateIsNullOrderByTotalTransactionDesc(topTen).stream().map(e -> {
                return modelMapper.map(e, AnalyticsMostProductResponse.class);
            }).collect(Collectors.toList());
        }
        Sellers seller = sellesRepository.findByUser(user);
        return productRepository.findAllByDeleteDateIsNullAndSellerOrderByTotalTransactionDesc(seller, topTen).stream().map(e -> {
            return modelMapper.map(e, AnalyticsMostProductResponse.class);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AnalyticsSellerResponse findAnalyticsSeller() {
        allAmount = 0;
        lastMonthAmount=0;
        Users users = authenticationFacade.getAuthentication();
        Sellers sellers = sellesRepository.findByUser(users);

        Date date = new Date();
        date.setMonth(new Date().getMonth()-1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String lastMonth = formatter.format(date);

        productTransactionRepository.amountProductTransactionThisSeller("", sellers.getSellerId()).forEach(e -> {
            allAmount+=e.getAmount();
        });
        productTransactionRepository.amountProductTransactionThisSeller(lastMonth, sellers.getSellerId()).forEach(e -> {
            lastMonthAmount+=e.getAmount();
        });
        return AnalyticsSellerResponse.builder()
                .transaction(productTransactionRepository.countProductTransactionThisSeller("", sellers.getSellerId()))
                .subTransaction(productTransactionRepository.countProductTransactionThisSeller(lastMonth, sellers.getSellerId()))
                .salesAmount(allAmount)
                .subSalesAmount(lastMonthAmount)
                .build();
    }
}
