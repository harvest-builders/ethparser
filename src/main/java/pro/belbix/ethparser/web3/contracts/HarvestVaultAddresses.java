package pro.belbix.ethparser.web3.contracts;

import static pro.belbix.ethparser.utils.CommonUtils.createUniqueMap;

import java.util.Map;

public class HarvestVaultAddresses {

    private HarvestVaultAddresses() {
    }

    static final Map<String, String> VAULTS = createUniqueMap(
        "YCRV_V0", "0xF2B223Eb3d2B382Ead8D85f3c1b7eF87c1D35f3A".toLowerCase(),
        "WETH_V0", "0x8e298734681adbfC41ee5d17FF8B0d6d803e7098".toLowerCase(),
        "USDC_V0", "0xc3F7ffb5d5869B3ade9448D094d81B0521e8326f".toLowerCase(),
        "USDT_V0", "0xc7EE21406BB581e741FBb8B21f213188433D9f2F".toLowerCase(),
        "DAI_V0", "0xe85C8581e60D7Cd32Bbfd86303d2A4FA6a951Dac".toLowerCase(),
        "WBTC_V0", "0xc07EB91961662D275E2D285BdC21885A4Db136B0".toLowerCase(),
        "RENBTC_V0", "0xfBe122D0ba3c75e1F7C80bd27613c9f35B81FEeC".toLowerCase(),
        "CRVRENWBTC_V0", "0x192E9d29D43db385063799BC239E772c3b6888F3".toLowerCase(),
        "UNI_ETH_DAI_V0", "0x1a9F22b4C385f78650E7874d64e442839Dc32327".toLowerCase(),
        "UNI_ETH_USDC_V0", "0x63671425ef4D25Ec2b12C7d05DE855C143f16e3B".toLowerCase(),
        "UNI_ETH_USDT_V0", "0xB19EbFB37A936cCe783142955D39Ca70Aa29D43c".toLowerCase(),
        "UNI_ETH_WBTC_V0", "0xb1FeB6ab4EF7d0f41363Da33868e85EB0f3A57EE".toLowerCase(),
        "UNI_ETH_DAI", "0x307E2752e8b8a9C29005001Be66B1c012CA9CDB7".toLowerCase(),
        "UNI_ETH_USDC", "0xA79a083FDD87F73c2f983c5551EC974685D6bb36".toLowerCase(),
        "UNI_ETH_USDT", "0x7DDc3ffF0612E75Ea5ddC0d6Bd4e268f70362Cff".toLowerCase(),
        "UNI_ETH_WBTC", "0x01112a60f427205dcA6E229425306923c3Cc2073".toLowerCase(),
        "WETH", "0xFE09e53A81Fe2808bc493ea64319109B5bAa573e".toLowerCase(),
        "USDC", "0xf0358e8c3CD5Fa238a29301d0bEa3D63A17bEdBE".toLowerCase(),
        "USDT", "0x053c80eA73Dc6941F518a68E2FC52Ac45BDE7c9C".toLowerCase(),
        "DAI", "0xab7FA2B2985BCcfC13c6D86b1D5A17486ab1e04C".toLowerCase(),
        "WBTC", "0x5d9d25c7C457dD82fc8668FFC6B9746b674d4EcB".toLowerCase(),
        "RENBTC", "0xC391d1b08c1403313B0c28D47202DFDA015633C4".toLowerCase(),
        "CRVRENWBTC", "0x9aA8F427A17d6B0d91B6262989EdC7D45d6aEdf8".toLowerCase(),
        "SUSHI_WBTC_TBTC", "0xF553E1f826f42716cDFe02bde5ee76b2a52fc7EB".toLowerCase(),
        "YCRV", "0x0FE4283e0216F94f5f9750a7a11AC54D3c9C38F3".toLowerCase(),
        "_3CRV", "0x71B9eC42bB3CB40F017D8AD8011BE8e384a95fa5".toLowerCase(),
        "TUSD", "0x7674622c63Bee7F46E86a4A5A18976693D54441b".toLowerCase(),
        "CRV_TBTC", "0x640704D106E79e105FDA424f05467F005418F1B5".toLowerCase(),
        "PS", "0x25550Cccbd68533Fa04bFD3e3AC4D09f9e00Fc50".toLowerCase(),
        "PS_V0", "0x59258F4e15A5fC74A7284055A8094F58108dbD4f".toLowerCase(),
        "CRV_CMPND", "0x998cEb152A42a3EaC1f555B1E911642BeBf00faD".toLowerCase(),
        "CRV_BUSD", "0x4b1cBD6F6D8676AcE5E412C78B7a59b4A1bbb68a".toLowerCase(),
        "CRV_USDN", "0x683E683fBE6Cf9b635539712c999f3B3EdCB8664".toLowerCase(),
        "SUSHI_ETH_DAI", "0x203E97aa6eB65A1A02d9E80083414058303f241E".toLowerCase(),
        "SUSHI_ETH_USDC", "0x01bd09A1124960d9bE04b638b142Df9DF942b04a".toLowerCase(),
        "SUSHI_ETH_USDT", "0x64035b583c8c694627A199243E863Bb33be60745".toLowerCase(),
        "SUSHI_ETH_WBTC", "0x5C0A3F55AAC52AA320Ff5F280E77517cbAF85524".toLowerCase(),
        "IDX_ETH_DPI", "0x2a32dcbb121d48c106f6d94cf2b4714c0b4dfe48".toLowerCase(),
        "CRV_HUSD", "0x29780C39164Ebbd62e9DDDE50c151810070140f2".toLowerCase(),
        "CRV_HBTC", "0xCC775989e76ab386E9253df5B0c0b473E22102E2".toLowerCase(),
        "UNI_BAC_DAI", "0x6Bccd7E983E438a56Ba2844883A664Da87E4C43b".toLowerCase(),
        "UNI_DAI_BAS", "0xf8b7235fcfd5a75cfdcc0d7bc813817f3dd17858".toLowerCase(),
        "SUSHI_MIC_USDT", "0x6F14165c6D529eA3Bfe1814d0998449e9c8D157D".toLowerCase(),
        "SUSHI_MIS_USDT", "0x145f39B3c6e6a885AA6A8fadE4ca69d64bab69c8".toLowerCase(),
        "CRV_OBTC", "0x966A70A4d3719A6De6a94236532A0167d5246c72".toLowerCase(),
        "ONEINCH_ETH_DAI", "0x8e53031462e930827a8d482e7d80603b1f86e32d".toLowerCase(),
        "ONEINCH_ETH_USDC", "0xd162395c21357b126c5afed6921bc8b13e48d690".toLowerCase(),
        "ONEINCH_ETH_USDT", "0x4bf633a09bd593f6fb047db3b4c25ef5b9c5b99e".toLowerCase(),
        "ONEINCH_ETH_WBTC", "0x859222dd0b249d0ea960f5102dab79b294d6874a".toLowerCase(),
        "DAI_BSG", "0x639d4f3F41daA5f4B94d63C2A5f3e18139ba9E54".toLowerCase(),
        "DAI_BSGS", "0x633C4861A4E9522353EDa0bb652878B079fb75Fd".toLowerCase(),
        "BAC", "0x371E78676cd8547ef969f89D2ee8fA689C50F86B".toLowerCase(),
        "ESD", "0x45a9e027DdD8486faD6fca647Bb132AD03303EC2".toLowerCase(),
        "DSD", "0x8Bf3c1c7B1961764Ecb19b4FC4491150ceB1ABB1".toLowerCase(),
        "CRV_EURS", "0x6eb941BD065b8a5bd699C5405A928c1f561e2e5a".toLowerCase(),
        "CRV_UST", "0x84A1DfAdd698886A614fD70407936816183C0A02".toLowerCase(),
        "MAAPL_UST", "0x11804D69AcaC6Ae9466798325fA7DE023f63Ab53".toLowerCase(),
        "MAMZN_UST", "0x8334A61012A779169725FcC43ADcff1F581350B7".toLowerCase(),
        "MGOOGL_UST", "0x07DBe6aA35EF70DaD124f4e2b748fFA6C9E1963a".toLowerCase(),
        "MTSLA_UST", "0xC800982d906671637E23E031e907d2e3487291Bc".toLowerCase(),
        "CRV_STETH", "0xc27bfE32E0a934a12681C1b35acf0DBA0e7460Ba".toLowerCase(),
        "CRV_GUSD", "0xB8671E33fcFC7FEA2F7a3Ea4a117F065ec4b009E".toLowerCase()
    );
}