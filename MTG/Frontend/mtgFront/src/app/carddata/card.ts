import { ImageUris } from './imageUris';
import { Legalities } from './legalities';
import { Prices } from './prices'; // Note: Ensure the file name matches the import
import { PurchaseUris } from './purchaseUris'; // Note: Ensure the file name matches the import
import { RelatedUris } from './relatedUris'; // Note: Ensure the file name matches the import

export class Card {
  soldBy: string;
  id: string;
  oracle_id: string;
  multiverse_ids: string[];
  mtgo_id: number;
  mtgo_foil_id: number;
  tcgplayer_id: number;
  cardmarket_id: number;
  name: string;
  lang: string;
  released_at: string;
  uri: string;
  scryfall_uri: string;
  layout: string;
  highres_image: boolean;
  image_status: string;
  image_uris: ImageUris;
  mana_cost: string;
  cmc: number;
  type_line: string;
  oracle_text: string;
  power: string;
  toughness: string;
  colors: string[];
  color_identity: string[];
  keywords: string[];
  legalities: Legalities;
  games: string[];
  reserved: boolean;
  foil: boolean;
  nonfoil: boolean;
  finishes: string[];
  oversized: boolean;
  promo: boolean;
  reprint: boolean;
  variation: boolean;
  set_id: string;
  set: string;
  set_name: string;
  set_type: string;
  set_uri: string;
  set_search_uri: string;
  scryfall_set_uri: string;
  rulings_uri: string;
  prints_search_uri: string;
  collector_number: string;
  digital: boolean;
  rarity: string;
  card_back_id: string;
  artist: string;
  artist_ids: string[];
  illustration_id: string;
  border_color: string;
  frame: string;
  full_art: boolean;
  textless: boolean;
  booster: boolean;
  story_spotlight: boolean;
  edhrec_rank: number;
  penny_rank: number;
  prices: Prices;
  related_uris: RelatedUris;
  purchase_uris: PurchaseUris;

  constructor(data: any) {
    this.soldBy = data.soldBy || null;
    this.id = data.id || null;
    this.oracle_id = data.oracle_id || null;
    this.multiverse_ids = data.multiverse_ids || null;
    this.mtgo_id = data.mtgo_id || null;
    this.mtgo_foil_id = data.mtgo_foil_id || null;
    this.tcgplayer_id = data.tcgplayer_id || null;
    this.cardmarket_id = data.cardmarket_id || null;
    this.name = data.name || null;
    this.lang = data.lang || null;
    this.released_at = data.released_at || null;
    this.uri = data.uri || null;
    this.scryfall_uri = data.scryfall_uri || null;
    this.layout = data.layout || null;
    this.highres_image = data.highres_image || null;
    this.image_status = data.image_status || null;
    this.image_uris = data.image_uris || null;
    this.mana_cost = data.mana_cost || null;
    this.cmc = data.cmc || null;
    this.type_line = data.type_line || null;
    this.oracle_text = data.oracle_text || null;
    this.power = data.power || null;
    this.toughness = data.toughness || null;
    this.colors = data.colors || null;
    this.color_identity = data.color_identity || null;
    this.keywords = data.keywords || null;
    this.legalities = data.legalities || null;
    this.games = data.games || null;
    this.reserved = data.reserved || null;
    this.foil = data.foil || null;
    this.nonfoil = data.nonfoil || null;
    this.finishes = data.finishes || null;
    this.oversized = data.oversized || null;
    this.promo = data.promo || null;
    this.reprint = data.reprint || null;
    this.variation = data.variation || null;
    this.set_id = data.set_id || null;
    this.set = data.set || null;
    this.set_name = data.set_name || null;
    this.set_type = data.set_type || null;
    this.set_uri = data.set_uri || null;
    this.set_search_uri = data.set_search_uri || null;
    this.scryfall_set_uri = data.scryfall_set_uri || null;
    this.rulings_uri = data.rulings_uri || null;
    this.prints_search_uri = data.prints_search_uri || null;
    this.collector_number = data.collector_number || null;
    this.digital = data.digital || null;
    this.rarity = data.rarity || null;
    this.card_back_id = data.card_back_id || null;
    this.artist = data.artist || null;
    this.artist_ids = data.artist_ids || null;
    this.illustration_id = data.illustration_id || null;
    this.border_color = data.border_color || null;
    this.frame = data.frame || null;
    this.full_art = data.full_art || null;
    this.textless = data.textless || null;
    this.booster = data.booster || null;
    this.story_spotlight = data.story_spotlight || null;
    this.edhrec_rank = data.edhrec_rank || null;
    this.penny_rank = data.penny_rank || null;
    this.prices = data.prices || null;
    this.related_uris = data.related_uris || null;
    this.purchase_uris = data.purchase_uris || null;
  }
}
