package com.cryptomarketplace.domain.model

import com.example.cryptomarketplace.R

enum class CoinType(
    val iconRes: Int,
    val coinNameShortcut: String,
    val usdToCoinShortcut: String,
) {
    BTC(
        iconRes = R.drawable.bitcoin_btc_logo,
        coinNameShortcut = "BTC",
        usdToCoinShortcut = "tBTCUSD",
    ),

    ETH(
        iconRes = R.drawable.ethereum_eth_logo,
        coinNameShortcut = "ETH",
        usdToCoinShortcut = "tETHUSD",
    ),

    CHSB(
        iconRes = R.drawable.swissborg_chsb_logo,
        coinNameShortcut = "CHSB",
        usdToCoinShortcut = "tCHSB:USD",
    ),

    LTC(
        iconRes = R.drawable.litecoin_ltc_logo,
        coinNameShortcut = "LTC",
        usdToCoinShortcut = "tLTCUSD",
    ),

    XRP(
        iconRes = R.drawable.xrp_xrp_logo,
        coinNameShortcut = "XRP",
        usdToCoinShortcut = "tXRPUSD",
    ),

    DSH(
        iconRes = R.drawable.dash_dash_logo,
        coinNameShortcut = "DSH",
        usdToCoinShortcut = "tDSHUSD",
    ),

    RRT(
        iconRes = R.drawable.bitfinex_rrt_logo,
        coinNameShortcut = "RRT",
        usdToCoinShortcut = "tRRTUSD",
    ),

    EOS(
        iconRes = R.drawable.eos_eos_logo,
        coinNameShortcut = "EOS",
        usdToCoinShortcut = "tEOSUSD",
    ),

    DAT(
        iconRes = R.drawable.bitpanda_best_logo,
        coinNameShortcut = "DAT",
        usdToCoinShortcut = "tDATUSD",
    ),

    SNT(
        iconRes = R.drawable.status_snt_logo,
        coinNameShortcut = "SNT",
        usdToCoinShortcut = "tSNTUSD",
    ),

    DOGE(
        iconRes = R.drawable.dogecoin_doge_logo,
        coinNameShortcut = "DOGE",
        usdToCoinShortcut = "tDOGE:USD",
    ),

    LUNA(
        iconRes = R.drawable.terra_luna_logo_com,
        coinNameShortcut = "LUNA",
        usdToCoinShortcut = "tLUNA:USD",
    ),

    MATIC(
        iconRes = R.drawable.polygon_matic_logo,
        coinNameShortcut = "MATIC",
        usdToCoinShortcut = "tMATIC:USD",
    ),

    NEXO(
        iconRes = R.drawable.nexo_nexo_logo,
        coinNameShortcut = "NEXO",
        usdToCoinShortcut = "tNEXO:USD",
    ),

    OCEAN(
        iconRes = R.drawable.ocean_protocol_ocean_logo,
        coinNameShortcut = "OCEAN",
        usdToCoinShortcut = "tOCEAN:USD",
    ),

    AAVE(
        iconRes = R.drawable.aave_aave_logo,
        coinNameShortcut = "AAVE",
        usdToCoinShortcut = "tAAVE:USD",
    ),

    PLU(
        iconRes = R.drawable.pluton_plu_logo,
        coinNameShortcut = "PLU",
        usdToCoinShortcut = "tPLUUSD",
    ),

    FIL(
        iconRes = R.drawable.filecoin_fil_logo,
        coinNameShortcut = "FIL",
        usdToCoinShortcut = "tFILUSD",
    );

    companion object {
        fun getCoinTypeByUSDtoCoinShortcut(usdToCoinShortcut: String): CoinType =
            entries.first { it.usdToCoinShortcut == usdToCoinShortcut }
    }
}
