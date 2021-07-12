package br.com.zupacademy.felipe.gadelha.mercadolivre.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.NotifierBuyer;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.ProcessPayInvoice;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.component.ProcessPayRanking;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.GatewayStatus;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.event.ProcessPayEvent;

@Component
public class ProcessPayListener {
	
	private final NotifierBuyer notifierBuyer;
	private final ProcessPayInvoice processPayInvoice;
	private final ProcessPayRanking processPayRanking;
	
	@Autowired
	public ProcessPayListener(NotifierBuyer notifierBuyer,
			ProcessPayInvoice processPayInvoice,
			ProcessPayRanking processPayRanking) {
		this.notifierBuyer = notifierBuyer;
		this.processPayInvoice = processPayInvoice;
		this.processPayRanking = processPayRanking;
	}
	
	@EventListener
	public void ProcessPayInvoiceListener(ProcessPayEvent event) {
		if (event.getStatusPay().equals(GatewayStatus.SUCCESS))
			processPayInvoice.process(event.getPurchase());
	}
	@EventListener
	public void ProcessPayRankingListener(ProcessPayEvent event) {
		if (event.getStatusPay().equals(GatewayStatus.SUCCESS))
			processPayRanking.process(event.getPurchase());
	}
	@EventListener
	public void notifierPurchaseListener(ProcessPayEvent event) {
		if (event.getStatusPay().equals(GatewayStatus.SUCCESS))
			notifierBuyer.newSuccessfulTransaction(event.getPurchase());
		else notifierBuyer.newfailTransaction(event.getPurchase());
	}
}
